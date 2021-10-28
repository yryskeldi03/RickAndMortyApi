package kg.geek.rickmortyapi.ui.search

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.navigation.fragment.findNavController
import kg.geek.rickmortyapi.R
import kg.geek.rickmortyapi.core.BaseFragment
import kg.geek.rickmortyapi.data.models.Character
import kg.geek.rickmortyapi.data.result.Status
import kg.geek.rickmortyapi.databinding.FragmentSearchBinding
import kg.geek.rickmortyapi.extensions.showToast
import kg.geek.rickmortyapi.extensions.visible
import kg.geek.rickmortyapi.ui.characters.CharactersFragment
import kg.geek.rickmortyapi.ui.episode.EpisodeFragment
import kg.geek.rickmortyapi.ui.location.LocationsFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

@SuppressLint("NotifyDataSetChanged")
class SearchFragment : BaseFragment<FragmentSearchBinding>() {
    private val viewModel: SearchViewModel by viewModel()
    private var list = arrayListOf<Character>()
    private val adapter: SearchAdapter by lazy { SearchAdapter(list, this::clickListener) }

    override fun setUI() {
        binding.btnSearch.setOnClickListener {
            if (binding.etSearch.text.trim().toString().isNotEmpty()) {
                list.clear()
                getFilteredData(binding.etSearch.text.trim().toString())
                binding.progressBar.visible = true
            }
        }
        binding.rvItems.adapter = adapter
    }

    override fun setupObservers() {
        viewModel.loading.observe(viewLifecycleOwner) {
            binding.progressBar.visible = it
        }
    }

    private fun getFilteredData(name: String) {

        viewModel.getLocationsByName(name).observe(viewLifecycleOwner) { response ->
            when (response.status) {
                Status.SUCCESS -> {
                    viewModel.loading.value = false
                    list.addAll(response.data?.results!!)
                    adapter.notifyDataSetChanged()
                }

                Status.LOADING -> viewModel.loading.value = true

                Status.ERROR -> {
                    viewModel.loading.value = false
                    requireContext().showToast(response.message.toString())
                }
            }
        }
        viewModel.getCharactersByName(name).observe(viewLifecycleOwner) { response ->
            when (response.status) {
                Status.SUCCESS -> {
                    viewModel.loading.value = false
                    list.addAll(response.data?.results!!)
                    adapter.notifyDataSetChanged()
                }

                Status.LOADING -> viewModel.loading.value = true

                Status.ERROR -> {
                    viewModel.loading.value = false
                    requireContext().showToast(response.message.toString())
                }
            }
        }
        viewModel.getEpisodesByName(name).observe(viewLifecycleOwner) { response ->
            when (response.status) {
                Status.SUCCESS -> {
                    viewModel.loading.value = false
                    list.addAll(response.data?.results!!)
                    adapter.notifyDataSetChanged()
                }

                Status.LOADING -> viewModel.loading.value = true

                Status.ERROR -> {
                    viewModel.loading.value = false
                    requireContext().showToast(response.code.toString())
                }
            }
        }


    }

    private fun clickListener(type: String, id: Int) {
        val bundle = Bundle()
        val objectKey = when (type) {
            CharactersFragment.CHARACTER_ID -> {
                CharactersFragment.CHARACTER_ID
            }
            LocationsFragment.LOCATION_ID -> {
                LocationsFragment.LOCATION_ID
            }
            else -> {
                EpisodeFragment.EPISODE_ID
            }
        }
        bundle.putSerializable(objectKey, id)
        findNavController().navigate(R.id.detailFragment, bundle)
    }

    override fun inflateViewBinding(): FragmentSearchBinding {
        return FragmentSearchBinding.inflate(layoutInflater)
    }

}