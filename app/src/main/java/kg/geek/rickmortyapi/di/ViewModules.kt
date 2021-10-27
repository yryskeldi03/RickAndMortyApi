package kg.geek.rickmortyapi.di

import kg.geek.rickmortyapi.ui.characters.CharactersViewModel
import kg.geek.rickmortyapi.ui.episode.EpisodesViewModel
import kg.geek.rickmortyapi.ui.location.LocationsViewModel
import kg.geek.rickmortyapi.ui.search.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val viewModules: Module = module {
    viewModel { CharactersViewModel(get()) }
    viewModel { LocationsViewModel(get()) }
    viewModel { EpisodesViewModel(get()) }
    viewModel { SearchViewModel(get()) }
}