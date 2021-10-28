package kg.geek.rickmortyapi.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import kg.geek.rickmortyapi.data.models.Character
import kg.geek.rickmortyapi.data.models.MainResult
import kg.geek.rickmortyapi.data.network.RickAndMortyApi
import kg.geek.rickmortyapi.data.result.Resource
import kotlinx.coroutines.Dispatchers

class SearchRepository(private val rickAndMortyApi: RickAndMortyApi) {

    fun getLocationsByName(name: String): LiveData<Resource<MainResult<Character>>> =
        liveData(Dispatchers.IO) {
            Resource.loading(null)
            val response = rickAndMortyApi.getLocationsByName(name)
            emit(
                if (response.isSuccessful) Resource.success(response.body())
                else Resource.error(
                    response.message(),
                    response.body(),
                    response.code()
                )
            )
        }

    fun getEpisodesByName(name: String): LiveData<Resource<MainResult<Character>>> =
        liveData(Dispatchers.IO) {
            Resource.loading(null)
            val response = rickAndMortyApi.getEpisodesByName(name)
            emit(
                if (response.isSuccessful) Resource.success(response.body()) else Resource.error(
                    response.message(),
                    response.body(),
                    response.code()
                )
            )
        }

    fun getCharactersByName(name: String): LiveData<Resource<MainResult<Character>>> =
        liveData(Dispatchers.IO) {
            Resource.loading(null)
            val response = rickAndMortyApi.getCharactersByName(name)
            emit(
                if (response.isSuccessful) Resource.success(response.body()) else Resource.error(
                    response.message(),
                    response.body(),
                    response.code()
                )
            )
        }
}