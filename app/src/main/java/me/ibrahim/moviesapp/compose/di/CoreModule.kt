package me.ibrahim.moviesapp.compose.di

import me.ibrahim.moviesapp.compose.data.database.DatabaseFactory
import me.ibrahim.moviesapp.compose.data.database.MoviesDatabase
import me.ibrahim.moviesapp.compose.presentation.favorite.FavoriteMoviesViewModel
import me.ibrahim.moviesapp.compose.presentation.movies_detail.MovieDetailViewModel
import me.ibrahim.moviesapp.compose.presentation.movies_list.MoviesListViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val coreModule = module {
    viewModelOf(::MoviesListViewModel)
    viewModelOf(::MovieDetailViewModel)
    viewModelOf(::FavoriteMoviesViewModel)

    single { DatabaseFactory.create(get()) }
    single { get<MoviesDatabase>().moviesDao }
}