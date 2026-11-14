package me.ibrahim.moviesapp.compose.di

import me.ibrahim.moviesapp.compose.data.repository.MoviesRepositoryImpl
import me.ibrahim.moviesapp.compose.domain.MoviesRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val repositoryModule = module {
    singleOf(::MoviesRepositoryImpl).bind<MoviesRepository>()
}