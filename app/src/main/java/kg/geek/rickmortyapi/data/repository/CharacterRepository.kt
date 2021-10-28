package kg.geek.rickmortyapi.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import kg.geek.rickmortyapi.data.models.Character
import kg.geek.rickmortyapi.data.models.MainResult
import kg.geek.rickmortyapi.data.network.RickAndMortyApi
import kg.geek.rickmortyapi.data.result.Resource
import kotlinx.coroutines.Dispatchers

class CharacterRepository(private val rickAndMortyApi: RickAndMortyApi) {

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

    fun getCharactersByStatus(
        status: String,
        page: Int
    ): LiveData<Resource<MainResult<Character>>> =
        liveData(Dispatchers.IO) {
            Resource.loading(null)
            val response = rickAndMortyApi.getCharacterByStatus(status, page)
            emit(
                if (response.isSuccessful) Resource.success(response.body())
                else Resource.error(
                    response.message(),
                    response.body(),
                    response.code()
                )
            )
        }

    fun getCharactersByGender(
        gender: String,
        page: Int
    ): LiveData<Resource<MainResult<Character>>> =
        liveData(Dispatchers.IO) {
            Resource.loading(null)
            val response = rickAndMortyApi.getCharacterByGender(gender, page)
            emit(
                if (response.isSuccessful) Resource.success(response.body())
                else Resource.error(
                    response.message(),
                    response.body(),
                    response.code()
                )
            )
        }

    fun getCharactersByStatusAndGender(
        status: String,
        gender: String,
        page: Int
    ): LiveData<Resource<MainResult<Character>>> =
        liveData(Dispatchers.IO) {
            Resource.loading(null)
            val response = rickAndMortyApi.getCharacterByStatusAndGender(status, gender, page)
            emit(
                if (response.isSuccessful) Resource.success(response.body())
                else Resource.error(
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