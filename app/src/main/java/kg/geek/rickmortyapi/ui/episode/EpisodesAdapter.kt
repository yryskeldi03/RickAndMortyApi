package kg.geek.rickmortyapi.ui.episode

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kg.geek.rickmortyapi.data.models.Episode
import kg.geek.rickmortyapi.databinding.EpisodeItemBinding

class EpisodesAdapter(
    private var episodes: ArrayList<Episode>,
    private val clickListener: (episodeId: Int) -> Unit
) :
    RecyclerView.Adapter<EpisodesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            EpisodeItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(episodes[position])
    }

    override fun getItemCount(): Int = episodes.size

    inner class ViewHolder(private val binding: EpisodeItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(episode: Episode) {

            binding.tvAirDate.text = episode.airDate
            binding.tvLocationName.text = episode.name

            itemView.setOnClickListener {
                clickListener(episode.id!!)
            }
        }
    }
}