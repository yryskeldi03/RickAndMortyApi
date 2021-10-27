package kg.geek.rickmortyapi.data.models

import com.google.gson.annotations.SerializedName

data class Episode(
    @SerializedName("air_date")
    var airDate: String?,
    var characters: List<Any>?,
    var created: String?,
    var episode: String?,
    var id: Int?,
    var name: String?,
    var url: String?
)