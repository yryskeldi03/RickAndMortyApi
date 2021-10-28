package kg.geek.rickmortyapi.ui.characters

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.navigation.fragment.findNavController
import kg.geek.rickmortyapi.R
import kg.geek.rickmortyapi.core.BaseFragment
import kg.geek.rickmortyapi.data.result.Status.*
import kg.geek.rickmortyapi.databinding.FragmentCharactersBinding
import kg.geek.rickmortyapi.extensions.visible
import kg.geek.rickmortyapi.extensions.showToast
import kg.geek.rickmortyapi.data.models.Character
import kg.geek.rickmortyapi.ui.dialog.CustomDialogFragment
import kg.geek.rickmortyapi.ui.dialog.CustomDialogResultListener
import org.koin.androidx.viewmodel.ext.android.viewModel

@SuppressLint("NotifyDataSetChanged")
class CharactersFragment : BaseFragment<FragmentCharactersBinding>() {
    private val viewModel: CharactersViewModel by viewModel()
    private var characters = arrayListOf<Character>()
    private var page = 1
    private val dialog: CustomDialogFragment by lazy { CustomDialogFragment() }
    private val male = "male"
    private val female = "female"
    private val genderless = "genderless"
    private val unknown = "unknown"
    private val alive = "alive"
    private val dead = "dead"
    private val adapter: CharactersAdapter by lazy {
        CharactersAdapter(
            characters,
            this::clickListener
        )
    }

    override fun setUI() {

        binding.ivFilter.setOnClickListener {
            dialog.show(parentFragmentManager, "customDialog")
        }

        filterResults()

    }

    private fun filterResults() {
        dialog.setListener(object : CustomDialogResultListener {
            override fun status(status: String?) {
                val resultStatus = when (status) {
                    getString(R.string.status_alive) -> alive

                    getString(R.string.status_dead) -> dead

                    else -> unknown

                }

                viewModel.getCharactersByStatus(resultStatus, page)
                    .observe(viewLifecycleOwner) { response ->
                        when (response.status) {
                            SUCCESS -> {
                                viewModel.loading.value = false
                                if (response.data?.results != null) {
                                    characters.clear()
                                    characters.addAll(response.data.results!!)
                                    adapter.notifyDataSetChanged()
                                }
                            }
                            LOADING -> viewModel.loading.value = true

                            ERROR -> {
                                viewModel.loading.value = false
                                requireContext().showToast(getString(R.string.error))
                            }
                        }
                    }
            }

            override fun gender(gender: String?) {
                val resultGender = when (gender) {
                    getString(R.string.male) -> male

                    getString(R.string.female) -> female

                    getString(R.string.genderless) -> genderless

                    else -> unknown
                }

                viewModel.getCharactersByGender(resultGender, page)
                    .observe(viewLifecycleOwner) { response ->
                        when (response.status) {
                            SUCCESS -> {
                                viewModel.loading.value = false
                                if (response.data?.results != null) {
                                    characters.clear()
                                    characters.addAll(response.data.results!!)
                                    adapter.notifyDataSetChanged()
                                    binding.rvCharacters.adapter = adapter
                                }
                            }
                            LOADING -> viewModel.loading.value = true

                            ERROR -> {
                                viewModel.loading.value = false
                                requireContext().showToast(getString(R.string.error))
                            }
                        }
                    }
            }

            override fun statusAndGender(status: String?, gender: String?) {
                val resultStatus = when (status) {
                    getString(R.string.status_alive) -> alive

                    getString(R.string.status_dead) -> dead

                    else -> unknown
                }

                val resultGender = when (gender) {
                    getString(R.string.male) -> male

                    getString(R.string.female) -> female

                    getString(R.string.genderless) -> genderless

                    else -> unknown
                }

                viewModel.getCharactersByStatusAndGender(resultStatus, resultGender, page)
                    .observe(viewLifecycleOwner) { response ->
                        when (response.status) {
                            SUCCESS -> {
                                viewModel.loading.value = false
                                if (response.data?.results != null) {
                                    characters.clear()
                                    characters.addAll(response.data.results!!)
                                    adapter.notifyDataSetChanged()
                                }
                            }
                            LOADING -> viewModel.loading.value = true

                            ERROR -> {
                                viewModel.loading.value = false
                                requireContext().showToast(getString(R.string.error))
                            }
                        }
                    }
            }

        })
    }

    override fun setupObservers() {

        viewModel.loading.observe(viewLifecycleOwner) {
            binding.progressBar.visible = it
        }

        viewModel.getCharacters(page).observe(viewLifecycleOwner) { response ->
            when (response.status) {
                SUCCESS -> {
                    viewModel.loading.value = false
                    if (response.data?.results != null) {
                        characters = response.data.results!!
                        adapter.notifyDataSetChanged()
                        binding.rvCharacters.adapter = adapter
                    }
                }
                LOADING -> viewModel.loading.value = true

                ERROR -> {
                    viewModel.loading.value = false
                    requireContext().showToast(response.message.toString())
                }
            }
        }

        binding.etCharacterName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(text: CharSequence?, start: Int, before: Int, count: Int) {
                binding.rvCharacters.visible = false
                viewModel.loading.value = true
            }

            override fun afterTextChanged(text: Editable?) {
                viewModel.getCharactersByName(text.toString())
                    .observe(viewLifecycleOwner) {
                        when (it.status) {
                            SUCCESS -> {
                                viewModel.loading.value = true
                                binding.rvCharacters.visible = true
                                if (it.data?.results != null) {
                                    viewModel.loading.value = false
                                    characters.clear()
                                    characters.addAll(it.data.results!!)
                                    adapter.notifyDataSetChanged()
                                }
                            }
                            LOADING -> viewModel.loading.value = true

                            ERROR -> {
                                viewModel.loading.value = false
                                requireContext().showToast(getString(R.string.error))
                            }
                        }
                    }
            }
        })
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