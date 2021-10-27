package kg.geek.rickmortyapi.ui.characters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kg.geek.rickmortyapi.R
import kg.geek.rickmortyapi.extensions.load
import kg.geek.rickmortyapi.data.models.Character
import kg.geek.rickmortyapi.databinding.CharacterItemBinding

class CharactersAdapter(
    private var characters: ArrayList<Character>,
    private val clickListener: (characterId: Int) -> Unit
) :
    RecyclerView.Adapter<CharactersAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            CharacterItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(characters[position])
    }

    override fun getItemCount(): Int = characters.size

    inner class ViewHolder(private val binding: CharacterItemBinding) :
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

            binding.root.setOnClickListener {
                clickListener(character.id!!)
            }
        }
    }
}
