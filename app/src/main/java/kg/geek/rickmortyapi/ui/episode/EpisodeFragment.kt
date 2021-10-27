package kg.geek.rickmortyapi.ui.episode

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kg.geek.rickmortyapi.R
import kg.geek.rickmortyapi.core.BaseFragment
import kg.geek.rickmortyapi.data.models.Episode
import kg.geek.rickmortyapi.data.result.Status
import kg.geek.rickmortyapi.databinding.FragmentEpisodesBinding
import kg.geek.rickmortyapi.extensions.showToast
import kg.geek.rickmortyapi.extensions.visible
import kg.geek.rickmortyapi.utils.Constants
import org.koin.androidx.viewmodel.ext.android.viewModel

class EpisodeFragment : BaseFragment<FragmentEpisodesBinding>() {
    private val viewModel: EpisodesViewModel by viewModel()
    private var episodes = arrayListOf<Episode>()
    private var page = 0
    private val adapter: EpisodesAdapter by lazy {
        EpisodesAdapter(
            episodes,
            this::clickListener
        )
    }

    override fun setUI() {

        binding.rvEpisodes.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                val layoutManager = binding.rvEpisodes.layoutManager as LinearLayoutManager
                val pos = layoutManager.findLastCompletelyVisibleItemPosition()
                val numItems: Int? = binding.rvEpisodes.adapter?.itemCount
                if (pos + 1 == numItems) {
                    setupObservers()
                    viewModel.loading.value = true
                }
            }
        })

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
                        val string = response.data.info?.next
                        val result = string?.replace(
                            Constants.EPISODES_PAGE_BASE_URL,
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