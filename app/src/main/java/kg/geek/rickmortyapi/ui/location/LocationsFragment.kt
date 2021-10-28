package kg.geek.rickmortyapi.ui.location

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.navigation.fragment.findNavController
import kg.geek.rickmortyapi.R
import kg.geek.rickmortyapi.core.BaseFragment
import kg.geek.rickmortyapi.data.models.Location
import kg.geek.rickmortyapi.data.result.Status
import kg.geek.rickmortyapi.databinding.FragmentLocationsBinding
import kg.geek.rickmortyapi.extensions.showToast
import kg.geek.rickmortyapi.extensions.visible
import org.koin.androidx.viewmodel.ext.android.viewModel

class LocationsFragment: BaseFragment<FragmentLocationsBinding>() {
    private val viewModel: LocationsViewModel by viewModel()
    private var locations = arrayListOf<Location>()
    private var page = 1
    private val adapter: LocationsAdapter by lazy {
        LocationsAdapter(
            locations,
            this::clickListener
        )
    }

    override fun setUI() {
        binding.rvLocations.adapter = adapter
    }
    @SuppressLint("NotifyDataSetChanged")
    override fun setupObservers() {

        viewModel.loading.observe(this) {
            binding.progressBar.visible = it
        }

        viewModel.getLocations(page).observe(viewLifecycleOwner) { response ->
            when (response.status) {
                Status.SUCCESS -> {
                    viewModel.loading.value = false
                    if (response.data?.results != null) {
                        locations.addAll(response.data.results!!)
                        adapter.notifyDataSetChanged()
                    }
                }

                Status.LOADING -> viewModel.loading.value = true

                Status.ERROR -> {
                    viewModel.loading.value = false
                    requireContext().showToast(response.message.toString())
                }
            }
        }

        binding.etLocationName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(text: CharSequence?, start: Int, before: Int, count: Int) {
                binding.rvLocations.visible = false
                viewModel.loading.value = true
            }

            override fun afterTextChanged(text: Editable?) {
                viewModel.getLocationsByName(text.toString())
                    .observe(viewLifecycleOwner) {
                        when (it.status) {
                            Status.SUCCESS -> {
                                viewModel.loading.value = true
                                binding.rvLocations.visible = true
                                if (it.data?.results != null) {
                                    viewModel.loading.value = false
                                    locations.clear()
                                    locations.addAll(it.data.results!!)
                                    adapter.notifyDataSetChanged()
                                }
                            }
                            Status.LOADING -> viewModel.loading.value = true

                            Status.ERROR -> {
                                viewModel.loading.value = false
                                requireContext().showToast(getString(R.string.error))
                            }
                        }
                    }
            }
        })
    }

    private fun clickListener(locationId: Int) {
        val bundle = Bundle()
        bundle.putSerializable(LOCATION_ID, locationId)
        findNavController().navigate(R.id.detailFragment, bundle)
    }

    override fun inflateViewBinding(): FragmentLocationsBinding {
        return FragmentLocationsBinding.inflate(layoutInflater)
    }

    companion object {
        const val LOCATION_ID = "locationId"
    }
}