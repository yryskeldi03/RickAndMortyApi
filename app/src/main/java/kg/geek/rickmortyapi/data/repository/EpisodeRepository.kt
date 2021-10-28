package kg.geek.rickmortyapi.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import kg.geek.rickmortyapi.data.models.Character
import kg.geek.rickmortyapi.data.models.Episode
import kg.geek.rickmortyapi.data.models.MainResult
import kg.geek.rickmortyapi.data.network.RickAndMortyApi
import kg.geek.rickmortyapi.data.result.Resource
import kotlinx.coroutines.Dispatchers

class EpisodeRepository(private val rickAndMortyApi: RickAndMortyApi) {

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

    fun getEpisodesByNameEpFragment(name: String): LiveData<Resource<MainResult<Episode>>> =
        liveData(Dispatchers.IO) {
            Resource.loading(null)
            val response = rickAndMortyApi.getEpisodesByNameEpFragment(name)
            emit(
                if (response.isSuccessful) Resource.success(response.body()) else Resource.error(
                    response.message(),
                    response.body(),
                    response.code()
                )
            )
        }
}