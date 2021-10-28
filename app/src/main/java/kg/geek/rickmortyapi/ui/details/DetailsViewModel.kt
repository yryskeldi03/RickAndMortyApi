package kg.geek.rickmortyapi.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kg.geek.rickmortyapi.data.result.Resource
import kg.geek.rickmortyapi.data.models.Character
import kg.geek.rickmortyapi.data.models.Episode
import kg.geek.rickmortyapi.data.models.Location
import kg.geek.rickmortyapi.data.repository.DetailRepository

class DetailsViewModel(private val repository: DetailRepository) : ViewModel() {

    val loading = MutableLiveData<Boolean>()

    fun getCharactersById(characterId: Int): LiveData<Resource<Character>> {
        return repository.getCharacterById(characterId)
    }

    fun getLocationById(locationId: Int): LiveData<Resource<Location>> {
        return repository.getLocationById(locationId)
    }

    fun getEpisodeById(characterId: Int): LiveData<Resource<Episode>> {
        return repository.getEpisodeById(characterId)
    }

}