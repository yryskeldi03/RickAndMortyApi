package kg.geek.rickmortyapi.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import kg.geek.rickmortyapi.data.network.RickAndMortyApi
import kg.geek.rickmortyapi.data.result.Resource
import kg.geek.rickmortyapi.data.models.Location
import kg.geek.rickmortyapi.data.models.MainResult
import kotlinx.coroutines.Dispatchers

class LocationRepository(private val rickAndMortyApi: RickAndMortyApi) {

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

    fun getLocationsByName(name: String): LiveData<Resource<MainResult<Location>>> =
        liveData(Dispatchers.IO) {
            Resource.loading(null)
            val response = rickAndMortyApi.getLocationsByNameLocFragment(name)
            emit(
                if (response.isSuccessful) Resource.success(response.body()) else Resource.error(
                    response.message(),
                    response.body(),
                    response.code()
                )
            )
        }
}