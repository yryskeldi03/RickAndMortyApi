package kg.geek.rickmortyapi.ui.location

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kg.geek.rickmortyapi.data.repository.LocationRepository
import kg.geek.rickmortyapi.data.result.Resource
import kg.geek.rickmortyapi.data.models.Location
import kg.geek.rickmortyapi.data.models.MainResult

class LocationsViewModel(private val repository: LocationRepository) : ViewModel() {

    val loading = MutableLiveData<Boolean>()

    fun getLocations(page: Int): LiveData<Resource<MainResult<Location>>> {
        return repository.getLocations(page)
    }

    fun getLocationsByName(name: String): LiveData<Resource<MainResult<Location>>> {
        return repository.getLocationsByName(name)
    }

}