package kg.geek.rickmortyapi.ui.location

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kg.geek.rickmortyapi.data.models.Location
import kg.geek.rickmortyapi.databinding.LocationItemBinding

class LocationsAdapter(
    private var locations: ArrayList<Location>,
    private val clickListener: (locationsId: Int) -> Unit
) :
    RecyclerView.Adapter<LocationsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LocationItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(locations[position])
    }

    override fun getItemCount(): Int = locations.size

    inner class ViewHolder(private val binding: LocationItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(location: Location) {
            binding.tvLocationName.text = location.name
            binding.tvType.text = location.type

            itemView.setOnClickListener {
                clickListener(location.id!!)
            }
        }
    }
}