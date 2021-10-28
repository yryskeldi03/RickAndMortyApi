package kg.geek.rickmortyapi.di

import kg.geek.rickmortyapi.data.repository.*
import org.koin.core.module.Module
import org.koin.dsl.module

val repoModules: Module = module {
    single { CharacterRepository(get()) }
    single { LocationRepository(get()) }
    single { EpisodeRepository(get()) }
    single { SearchRepository(get()) }
    single { DetailRepository(get()) }
}