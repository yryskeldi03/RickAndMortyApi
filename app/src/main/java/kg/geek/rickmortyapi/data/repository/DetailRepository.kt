package kg.geek.rickmortyapi.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import kg.geek.rickmortyapi.data.models.Character
import kg.geek.rickmortyapi.data.models.Episode
import kg.geek.rickmortyapi.data.models.Location
import kg.geek.rickmortyapi.data.network.RickAndMortyApi
import kg.geek.rickmortyapi.data.result.Resource
import kotlinx.coroutines.Dispatchers

class DetailRepository(private val rickAndMortyApi: RickAndMortyApi) {

    fun getCharacterById(characterId: Int): LiveData<Resource<Character>> =
        liveData(Dispatchers.IO) {
            Resource.loading(null)
            val response = rickAndMortyApi.getCharacterById(characterId)
            emit(
                if (response.isSuccessful) Resource.success(response.body())
                else Resource.error(
                    response.message(),
                    response.body(),
                    response.code()
                )
            )
        }

    fun getEpisodeById(locationsId: Int): LiveData<Resource<Episode>> = liveData(Dispatchers.IO) {
        Resource.loading(null)
        val response = rickAndMortyApi.getEpisodeById(locationsId)
        emit(
            if (response.isSuccessful) Resource.success(response.body()) else Resource.error(
                response.message(),
                response.body(),
                response.code()
            )
        )
    }

    fun getLocationById(locationsId: Int): LiveData<Resource<Location>> = liveData(Dispatchers.IO) {
        Resource.loading(null)
        val response = rickAndMortyApi.getLocationById(locationsId)
        emit(
            if (response.isSuccessful) Resource.success(response.body()) else Resource.error(
                response.message(),
                response.body(),
                response.code()
            )
        )
    }

}