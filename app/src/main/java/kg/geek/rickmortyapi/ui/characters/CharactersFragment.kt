package kg.geek.rickmortyapi.ui.characters

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kg.geek.rickmortyapi.R
import kg.geek.rickmortyapi.core.BaseFragment
import kg.geek.rickmortyapi.data.result.Status.*
import kg.geek.rickmortyapi.databinding.FragmentCharactersBinding
import kg.geek.rickmortyapi.extensions.visible
import kg.geek.rickmortyapi.extensions.showToast
import kg.geek.rickmortyapi.data.models.Character
import kg.geek.rickmortyapi.ui.dialog.CustomDialogFragment
import kg.geek.rickmortyapi.utils.Constants
import org.koin.androidx.viewmodel.ext.android.viewModel

class CharactersFragment : BaseFragment<FragmentCharactersBinding>() {
    private val viewModel: CharactersViewModel by viewModel()
    private var characters = arrayListOf<Character>()
    private var nextPage = 0
    private val adapter: CharactersAdapter by lazy {
        CharactersAdapter(
            characters,
            this::clickListener
        )
    }

    override fun setUI() {

        binding.rvCharacters.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                val layoutManager = binding.rvCharacters.layoutManager as LinearLayoutManager
                val pos = layoutManager.findLastCompletelyVisibleItemPosition()
                val numItems: Int? = binding.rvCharacters.adapter?.itemCount
                if (pos + 1 == numItems) {
                    setupObservers()
                    viewModel.loading.value = true
                }
            }
        })

        binding.rvCharacters.adapter = adapter
        
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun setupObservers() {

        viewModel.loading.observe(this) {
            binding.progressBar.visible = it
        }

        viewModel.getCharacters(nextPage).observe(viewLifecycleOwner) { response ->
            when (response.status) {
                SUCCESS -> {
                    viewModel.loading.value = false
                    if (response.data?.results != null) {
                        characters.addAll(response.data.results!!)
                        val string = response.data.info?.next
                        val result = string?.replace(
                            Constants.CHARACTERS_PAGE_BASE_URL,
                            "",
                            true
                        )
                        nextPage = result?.toInt() ?: 0
                        adapter.notifyDataSetChanged()
                    }
                }

                LOADING -> viewModel.loading.value = true

                ERROR -> {
                    viewModel.loading.value = false
                    requireContext().showToast(response.message.toString())
                }
            }
        }
    }

    private fun clickListener(characterId: Int) {
        val bundle = Bundle()
        bundle.putSerializable(CHARACTER_ID, characterId)
        findNavController().navigate(R.id.detailFragment, bundle)
    }

    override fun inflateViewBinding(): FragmentCharactersBinding {
        return FragmentCharactersBinding.inflate(layoutInflater)
    }

    companion object {
        const val CHARACTER_ID = "characterId"
    }
}