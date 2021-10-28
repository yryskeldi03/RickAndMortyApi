package kg.geek.rickmortyapi.ui.characters

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kg.geek.rickmortyapi.data.repository.CharacterRepository
import kg.geek.rickmortyapi.data.result.Resource
import kg.geek.rickmortyapi.data.models.Character
import kg.geek.rickmortyapi.data.models.MainResult

class CharactersViewModel(private val repository: CharacterRepository) : ViewModel() {

    val loading = MutableLiveData<Boolean>()

    fun getCharacters(page: Int): LiveData<Resource<MainResult<Character>>> {
        return repository.getCharacters(page)
    }

    fun getCharactersByName(name: String): LiveData<Resource<MainResult<Character>>> {
        return repository.getCharactersByName(name)
    }

    fun getCharactersByStatus(
        status: String,
        page: Int
    ): LiveData<Resource<MainResult<Character>>> {
        return repository.getCharactersByStatus(status, page)
    }

    fun getCharactersByGender(
        gender: String,
        page: Int
    ): LiveData<Resource<MainResult<Character>>> {
        return repository.getCharactersByGender(gender, page)
    }

    fun getCharactersByStatusAndGender(
        status: String,
        gender: String,
        page: Int
    ): LiveData<Resource<MainResult<Character>>> {
        return repository.getCharactersByStatusAndGender(status, gender, page)
    }

}