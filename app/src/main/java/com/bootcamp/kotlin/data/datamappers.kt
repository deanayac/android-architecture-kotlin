package com.bootcamp.kotlin.data

import com.bootcamp.kotlin.data.database.entity.InputSearch as InputSearchEntity
import com.bootcamp.kotlin.data.server.MovieDbResult
import com.movies.domain.Genre
import com.movies.domain.InputSearch as InputSearchDomain
import com.movies.domain.Movie
import com.movies.domain.PopularMovie
import com.bootcamp.kotlin.data.server.Movie as ServerMovie

fun MovieDbResult.toDomainMovie(): Movie {
    val genres = ArrayList<Genre>()

    this.genres.forEach { (id, name) ->
        genres.add(Genre(id, name))
    }

    return Movie(
        backDropPath,
        genres,
        id,
        originalTitle,
        overview,
        posterPath,
        releaseData,
        title,
        video,
        voteAverage
    )
}

fun List<ServerMovie>.toDomainPopularMovie(): List<PopularMovie> {
    val popularMovies = mutableListOf<PopularMovie>()
    this.map { movie ->
        with(movie) {
            popularMovies.add(
                PopularMovie(
                    backdropPath,
                    genreIds,
                    id,
                    originalTitle,
                    overview,
                    posterPath,
                    releaseDate,
                    title,
                    video,
                    voteAverage
                )
            )
        }
    }
    return popularMovies
}

fun InputSearchDomain.toDomainToEntity():InputSearchEntity = InputSearchEntity(0,description)

fun List<InputSearchEntity>.toEntityInDomain():List<InputSearchDomain>{
    val inputSearch = mutableListOf<InputSearchDomain>()
    this.map { input ->
        with(input){
            inputSearch.add(InputSearchDomain(description))
        }
    }
    return  inputSearch
}