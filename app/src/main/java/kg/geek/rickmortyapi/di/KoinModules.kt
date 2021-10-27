package kg.geek.rickmortyapi.di

import kg.geek.rickmortyapi.data.network.networkModule

val koinModules = listOf(
    networkModule,
    repoModules,
    viewModules,
    repoModules
)