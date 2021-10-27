package kg.geek.rickmortyapi.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kg.geek.rickmortyapi.R
import kg.geek.rickmortyapi.data.models.*
import kg.geek.rickmortyapi.databinding.CharacterItemBinding
import kg.geek.rickmortyapi.databinding.EpisodeItemBinding
import kg.geek.rickmortyapi.databinding.LocationItemBinding
import kg.geek.rickmortyapi.extensions.load
import kg.geek.rickmortyapi.ui.characters.CharactersFragment
import kg.geek.rickmortyapi.ui.episode.EpisodeFragment
import kg.geek.rickmortyapi.ui.location.LocationsFragment
import kg.geek.rickmortyapi.utils.Constants

class SearchAdapter(
    private var filteredItems: ArrayList<Character>,
    private var clickListener: (type: String, id: Int) -> Unit
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            CHARACTER -> {
                return CharacterViewHolder(
                    CharacterItemBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }

            LOCATION -> {
                return LocationViewHolder(
                    LocationItemBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
            else -> {
                return EpisodeViewHolder(
                    EpisodeItemBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when {
            getItemViewType(position) == CHARACTER -> {
                (holder as CharacterViewHolder).onBind(filteredItems[position])
            }
            getItemViewType(position) == LOCATION -> {
                (holder as LocationViewHolder).onBind(filteredItems[position])
            }
            else -> {
                (holder as EpisodeViewHolder).onBind(filteredItems[position])
            }
        }
    }

    override fun getItemCount(): Int = filteredItems.size

    override fun getItemViewType(position: Int): Int {
        return when {
            filteredItems[position].image?.isNotEmpty() == true -> {
                CHARACTER
            }
            filteredItems[position].dimension?.isNotEmpty() == true -> {
                LOCATION
            }
            else -> {
                EPISODE
            }
        }
    }

    inner class CharacterViewHolder(private val binding: CharacterItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(character: Character) {
            binding.ivItemCharacter.load(character.image.toString())
            binding.tvCharacterName.text = character.name
            binding.tvGender.text = character.gender
            binding.tvSpecies.text = character.species
            binding.tvStatus.text = String.format(character.status + " - ")

            when (character.status) {
                itemView.context.getString(R.string.status_alive) -> {
                    binding.statusColor.setBackgroundResource(R.drawable.alive_status)
                }
                itemView.context.getString(R.string.status_dead) -> {
                    binding.statusColor.setBackgroundResource(R.drawable.dead_status)
                }
                else -> {
                    binding.statusColor.setBackgroundResource(R.drawable.unknown_status)
                }
            }

            itemView.setOnClickListener {
                character.id?.let { it1 -> clickListener(CharactersFragment.CHARACTER_ID, it1) }
            }
        }
    }

    inner class LocationViewHolder(private val binding: LocationItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(location: Character) {
            binding.tvLocationName.text = location.name
            binding.tvType.text = location.type

                itemView.setOnClickListener {
                    location.id?.let { it1 -> clickListener(LocationsFragment.LOCATION_ID, it1) }
            }
        }
    }

    inner class EpisodeViewHolder(private val binding: EpisodeItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(episode: Character) {
            binding.tvAirDate.text = episode.airDate
            binding.tvLocationName.text = episode.name

            itemView.setOnClickListener {
                episode.id?.let { it1 -> clickListener(EpisodeFragment.EPISODE_ID, it1) }
            }
        }
    }

    companion object {
        const val CHARACTER = 1
        const val LOCATION = 2
        const val EPISODE = 3
    }
}