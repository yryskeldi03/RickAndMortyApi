package kg.geek.rickmortyapi.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kg.geek.rickmortyapi.data.repository.SearchRepository
import kg.geek.rickmortyapi.data.result.Resource
import kg.geek.rickmortyapi.data.models.Character
import kg.geek.rickmortyapi.data.models.MainResult

class SearchViewModel(private val repository: SearchRepository) : ViewModel() {

    val loading = MutableLiveData<Boolean>()

    fun getLocationsByName(name: String): LiveData<Resource<MainResult<Character>>> {
        return repository.getLocationsByName(name)
    }

    fun getCharactersByName(name: String): LiveData<Resource<MainResult<Character>>> {
        return repository.getCharactersByName(name)
    }

    fun getEpisodesByName(name: String): LiveData<Resource<MainResult<Character>>> {
        return repository.getEpisodesByName(name)
    }

}