package kg.geek.rickmortyapi.ui.episode

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.navigation.fragment.findNavController
import kg.geek.rickmortyapi.R
import kg.geek.rickmortyapi.core.BaseFragment
import kg.geek.rickmortyapi.data.models.Episode
import kg.geek.rickmortyapi.data.result.Status
import kg.geek.rickmortyapi.databinding.FragmentEpisodesBinding
import kg.geek.rickmortyapi.extensions.showToast
import kg.geek.rickmortyapi.extensions.visible
import org.koin.androidx.viewmodel.ext.android.viewModel

class EpisodeFragment : BaseFragment<FragmentEpisodesBinding>() {
    private val viewModel: EpisodesViewModel by viewModel()
    private var episodes = arrayListOf<Episode>()
    private var page = 1
    private val adapter: EpisodesAdapter by lazy {
        EpisodesAdapter(
            episodes,
            this::clickListener
        )
    }

    override fun setUI() {
        binding.rvEpisodes.adapter = adapter
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun setupObservers() {

        viewModel.loading.observe(this) {
            binding.progressBar.visible = it
        }

        viewModel.getEpisodes(page).observe(viewLifecycleOwner) { response ->
            when (response.status) {
                Status.SUCCESS -> {
                    viewModel.loading.value = false
                    if (response.data?.results != null) {
                        episodes.addAll(response.data.results!!)
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

        binding.etEpisodeName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(text: CharSequence?, start: Int, before: Int, count: Int) {
                binding.rvEpisodes.visible = false
                viewModel.loading.value = true
            }

            override fun afterTextChanged(text: Editable?) {
                viewModel.getEpisodesByName(text.toString())
                    .observe(viewLifecycleOwner) {
                        when (it.status) {
                            Status.SUCCESS -> {
                                viewModel.loading.value = true
                                binding.rvEpisodes.visible = true
                                if (it.data?.results != null) {
                                    viewModel.loading.value = false
                                    episodes.clear()
                                    episodes.addAll(it.data.results!!)
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

    private fun clickListener(episodeId: Int) {
        val bundle = Bundle()
        bundle.putSerializable(EPISODE_ID, episodeId)
        findNavController().navigate(R.id.detailFragment, bundle)
    }

    override fun inflateViewBinding(): FragmentEpisodesBinding {
        return FragmentEpisodesBinding.inflate(layoutInflater)
    }

    companion object {
        const val EPISODE_ID = "episodeId"
    }
}