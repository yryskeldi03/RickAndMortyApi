package kg.geek.rickmortyapi.ui.location

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kg.geek.rickmortyapi.R
import kg.geek.rickmortyapi.core.BaseFragment
import kg.geek.rickmortyapi.data.models.Location
import kg.geek.rickmortyapi.data.result.Status
import kg.geek.rickmortyapi.databinding.FragmentLocationsBinding
import kg.geek.rickmortyapi.extensions.showToast
import kg.geek.rickmortyapi.extensions.visible
import kg.geek.rickmortyapi.utils.Constants
import org.koin.androidx.viewmodel.ext.android.viewModel

class LocationsFragment: BaseFragment<FragmentLocationsBinding>() {
    private val viewModel: LocationsViewModel by viewModel()
    private var locations = arrayListOf<Location>()
    private var page = 0
    private val adapter: LocationsAdapter by lazy {
        LocationsAdapter(
            locations,
            this::clickListener
        )
    }

    override fun setUI() {

        binding.rvLocations.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                val layoutManager = binding.rvLocations.layoutManager as LinearLayoutManager
                val pos = layoutManager.findLastCompletelyVisibleItemPosition()
                val numItems: Int? = binding.rvLocations.adapter?.itemCount
                if (pos + 1 == numItems) {
                    viewModel.loading.value = true
                    setupObservers()
                }
            }
        })

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
                        val string = response.data.info?.next
                        val result = string?.replace(
                            Constants.LOCATIONS_PAGE_BASE_URL,
                            "",
                            true
                        )
                        page = result?.toInt() ?: 0
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