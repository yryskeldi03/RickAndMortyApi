package kg.geek.rickmortyapi.ui.search

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kg.geek.rickmortyapi.data.repository.Repository
import kg.geek.rickmortyapi.data.result.Resource
import kg.geek.rickmortyapi.data.models.Character
import kg.geek.rickmortyapi.data.models.Episode
import kg.geek.rickmortyapi.data.models.Location
import kg.geek.rickmortyapi.data.models.MainResult

class SearchViewModel(private val repository: Repository) : ViewModel() {

    val loading = MutableLiveData<Boolean>()

    fun getFilteredLocations(name: String): LiveData<Resource<MainResult<Character>>> {
        return repository.getFilteredLocations(name)
    }

    fun getFilteredCharacters(name: String): LiveData<Resource<MainResult<Character>>> {
        return repository.getFilteredCharacters(name)
    }

    fun getFilteredEpisodes(name: String): LiveData<Resource<MainResult<Character>>> {
        return repository.getFilteredEpisodes(name)
    }

}