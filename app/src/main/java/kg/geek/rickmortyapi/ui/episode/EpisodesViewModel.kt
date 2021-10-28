package kg.geek.rickmortyapi.ui.episode

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kg.geek.rickmortyapi.data.repository.EpisodeRepository
import kg.geek.rickmortyapi.data.result.Resource
import kg.geek.rickmortyapi.data.models.Episode
import kg.geek.rickmortyapi.data.models.MainResult

class EpisodesViewModel(private val repository: EpisodeRepository): ViewModel() {

    val loading = MutableLiveData<Boolean>()

    fun getEpisodes(page: Int): LiveData<Resource<MainResult<Episode>>>{
        return repository.getEpisodes(page)
    }

    fun getEpisodesByName(name: String): LiveData<Resource<MainResult<Episode>>> {
        return repository.getEpisodesByNameEpFragment(name)
    }

}