package kg.geek.rickmortyapi.ui.details

import kg.geek.rickmortyapi.R
import kg.geek.rickmortyapi.core.BaseFragment
import kg.geek.rickmortyapi.databinding.FragmentDetailBinding
import kg.geek.rickmortyapi.extensions.load
import kg.geek.rickmortyapi.extensions.visible
import kg.geek.rickmortyapi.ui.characters.CharactersFragment
import kg.geek.rickmortyapi.ui.episode.EpisodeFragment
import kg.geek.rickmortyapi.ui.location.LocationsFragment
import kg.geek.rickmortyapi.ui.search.SearchFragment
import kg.geek.rickmortyapi.utils.Constants
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailFragment : BaseFragment<FragmentDetailBinding>() {
    private val viewModel: DetailsViewModel by viewModel()
    private var characterId = 0
    private var locationId = 0
    private var episodeId = 0

    override fun setUI() {
        characterId = arguments?.getInt(CharactersFragment.CHARACTER_ID)!!
        locationId = arguments?.getInt(LocationsFragment.LOCATION_ID)!!
        episodeId = arguments?.getInt(EpisodeFragment.EPISODE_ID)!!
    }

    override fun setupObservers() {

        when {
            characterId != 0 -> {
                viewModel.getCharactersById(characterId).observe(viewLifecycleOwner) {
                    val data = it.data
                    data?.image?.let { it1 -> binding.ivAvatar.load(it1) }
                    binding.tvFirst.text = data?.name
                    binding.tvSecondTitle.text = getString(R.string.gender)
                    binding.tvSecond.text = data?.gender
                    binding.tvThirdTitle.text = getString(R.string.status)
                    binding.tvThird.text = data?.status
                    binding.tvFourthTitle.text = getString(R.string.species)
                    binding.tvFourth.text = data?.species
                    binding.tvFifthTitle.text = getString(R.string.location)
                    binding.tvFifth.text = data?.location?.name
                }
            }
            locationId != 0 -> {
                viewModel.getLocationById(locationId).observe(viewLifecycleOwner) {
                    val data = it.data
                    binding.ivAvatar.visible = false
                    binding.tvFirst.text = data?.name
                    binding.tvSecondTitle.text = getString(R.string.type)
                    binding.tvSecond.text = data?.type
                    binding.tvThirdTitle.text = getString(R.string.dimension)
                    binding.tvThird.text = data?.dimension
                    binding.tvFourthTitle.text = getString(R.string.created)
                    binding.tvFourth.text = data?.created
                    binding.tvFifthTitle.visible = false
                }
            }
            else -> {
                viewModel.getEpisodeById(episodeId).observe(viewLifecycleOwner) {
                    val data = it.data
                    binding.ivAvatar.visible = false
                    binding.tvFirst.text = data?.name
                    binding.tvSecondTitle.text = getString(R.string.air_date)
                    binding.tvSecond.text = data?.airDate
                    binding.tvThirdTitle.text = getString(R.string.episode_title)
                    binding.tvThird.text = data?.episode
                    binding.tvFourthTitle.text = getString(R.string.created)
                    binding.tvFourth.text = data?.created
                    binding.tvFifthTitle.visible = false
                }
            }
        }
    }

    override fun inflateViewBinding(): FragmentDetailBinding {
        return FragmentDetailBinding.inflate(layoutInflater)
    }
}