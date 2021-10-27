package kg.geek.rickmortyapi.ui.characters

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.material.chip.ChipGroup
import kg.geek.rickmortyapi.data.repository.Repository
import kg.geek.rickmortyapi.data.result.Resource
import kg.geek.rickmortyapi.data.models.Character
import kg.geek.rickmortyapi.data.models.MainResult

class CharactersViewModel(private val repository: Repository): ViewModel() {

    val loading = MutableLiveData<Boolean>()

    fun getCharacters(page: Int): LiveData<Resource<MainResult<Character>>>{
        return repository.getCharacters(page)
    }

}