package kg.geek.rickmortyapi.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import kg.geek.rickmortyapi.data.network.RickAndMortyApi
import kg.geek.rickmortyapi.data.result.Resource
import kg.geek.rickmortyapi.data.models.Character
import kg.geek.rickmortyapi.data.models.Episode
import kg.geek.rickmortyapi.data.models.Location
import kg.geek.rickmortyapi.data.models.MainResult
import kotlinx.coroutines.Dispatchers

class Repository(private val rickAndMortyApi: RickAndMortyApi) {

    fun getCharacters(page: Int): LiveData<Resource<MainResult<Character>>> =
        liveData(Dispatchers.IO) {
            Resource.loading(null)
            val response = rickAndMortyApi.getCharacters(page)
            emit(
                if (response.isSuccessful) Resource.success(response.body())
                else Resource.error(
                    response.message(),
                    response.body(),
                    response.code()
                )
            )
        }

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

    fun getLocations(page: Int): LiveData<Resource<MainResult<Location>>> =
        liveData(Dispatchers.IO) {
            Resource.loading(null)
            val response = rickAndMortyApi.getLocations(page)
            emit(
                if (response.isSuccessful) Resource.success(response.body())
                else Resource.error(
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

    fun getEpisodes(page: Int): LiveData<Resource<MainResult<Episode>>> =
        liveData(Dispatchers.IO) {
            Resource.loading(null)
            val response = rickAndMortyApi.getEpisodes(page)
            emit(
                if (response.isSuccessful) Resource.success(response.body()) else Resource.error(
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

    fun getFilteredLocations(name: String): LiveData<Resource<MainResult<Character>>> =
        liveData(Dispatchers.IO) {
            Resource.loading(null)
            val response = rickAndMortyApi.getFilteredLocations(name)
            Log.e("ololo", "getFilteredLocations: repository $response" )
            emit(
                if (response.isSuccessful) Resource.success(response.body())
                else Resource.error(
                    response.message(),
                    response.body(),
                    response.code()
                )
            )
        }

    fun getFilteredCharacters(name: String): LiveData<Resource<MainResult<Character>>> =
        liveData(Dispatchers.IO) {
            Resource.loading(null)
            val response = rickAndMortyApi.getFilteredCharacters(name)
            emit(
                if (response.isSuccessful) Resource.success(response.body()) else Resource.error(
                    response.message(),
                    response.body(),
                    response.code()
                )
            )
        }

    fun getFilteredEpisodes(name: String): LiveData<Resource<MainResult<Character>>> =
        liveData(Dispatchers.IO) {
            Log.e("ololo", "getFilteredEpisodes: repository " )
            Resource.loading(null)
            val response = rickAndMortyApi.getFilteredEpisodes(name)
            Log.e("ololo", "getFilteredLocations: repository episode response $response" )
            emit(
                if (response.isSuccessful) Resource.success(response.body()) else Resource.error(
                    response.message(),
                    response.body(),
                    response.code()
                )
            )
        }
}