package me.ibrahim.moviesapp.compose.data.mappers

import me.ibrahim.moviesapp.compose.BuildConfig
import me.ibrahim.moviesapp.compose.data.database.MovieEntity
import me.ibrahim.moviesapp.compose.data.dto.Cast
import me.ibrahim.moviesapp.compose.data.dto.MovieDto
import me.ibrahim.moviesapp.compose.domain.Actor
import me.ibrahim.moviesapp.compose.domain.Movie

fun MovieDto.toMovie(): Movie {

    return Movie(
        backdropPath = "${BuildConfig.BACKDROP_IMAGES_BASEURL}$backdropPath",
        id = id,
        originalLanguage = originalLanguage,
        originalTitle = originalTitle,
        overview = overview,
        popularity = popularity,
        posterPath = "${BuildConfig.POSTER_IMAGES_BASEURL}$posterPath",
        releaseDate = releaseDate,
        title = title,
        video = video,
        voteAverage = voteAverage,
        voteCount = voteCount
    )
}

fun Movie.toMovieEntity(): MovieEntity {
    return MovieEntity(
        id = id,
        backdropPath = backdropPath,
        originalLanguage = originalLanguage,
        originalTitle = originalTitle,
        overview = overview,
        popularity = popularity,
        posterPath = posterPath,
        releaseDate = releaseDate,
        title = title,
        video = video,
        voteAverage = voteAverage,
        voteCount = voteCount
    )
}

fun MovieEntity.toMovie(): Movie {
    return Movie(
        backdropPath = backdropPath,
        id = id,
        originalLanguage = originalLanguage,
        originalTitle = originalTitle,
        overview = overview,
        popularity = popularity,
        posterPath = posterPath,
        releaseDate = releaseDate,
        title = title,
        video = video,
        voteAverage = voteAverage,
        voteCount = voteCount
    )
}

fun Cast.toActor(): Actor {
    return Actor(
        castId = castId,
        character = character,
        creditId = creditId,
        id = id ?: -1,
        knownForDepartment = knownForDepartment,
        name = name,
        originalName = originalName,
        profilePath = profilePath
    )
}