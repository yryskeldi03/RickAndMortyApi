package kg.geek.rickmortyapi.ui.location

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kg.geek.rickmortyapi.data.repository.Repository
import kg.geek.rickmortyapi.data.result.Resource
import kg.geek.rickmortyapi.data.models.Character
import kg.geek.rickmortyapi.data.models.Location
import kg.geek.rickmortyapi.data.models.MainResult

class LocationsViewModel(private val repository: Repository) : ViewModel() {

    val loading = MutableLiveData<Boolean>()

    fun getLocations(page: Int): LiveData<Resource<MainResult<Location>>> {
        return repository.getLocations(page)
    }

}