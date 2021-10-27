package kg.geek.rickmortyapi.di

import kg.geek.rickmortyapi.data.repository.Repository
import kg.geek.rickmortyapi.ui.details.DetailsViewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val repoModules: Module = module {
    single { Repository(get()) }
    single { DetailsViewModel(get()) }
}