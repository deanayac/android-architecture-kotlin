package com.bootcamp.kotlin.data

import com.bootcamp.kotlin.data.database.entity.InputSearch as InputSearchEntity
import com.bootcamp.kotlin.data.server.MovieDbResult
import com.bootcamp.kotlin.data.server.MovieImages
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

fun MovieImages.toDomainMovieImages(): com.movies.domain.MovieImages {
    val backdrops = ArrayList<com.movies.domain.Backdrops>()
    val posters = ArrayList<com.movies.domain.Posters>()

    this.backdrops.forEach { backdrop ->
        with(backdrop) {
            backdrops.add(
                com.movies.domain.Backdrops(
                    aspect_ratio,
                    file_path,
                    height, iso_639_1,
                    vote_average,
                    vote_count, width
                )
            )
        }
    }

    this.posters.forEach { poster ->
        with(poster) {
            posters.add(
                com.movies.domain.Posters(
                    aspect_ratio,
                    file_path,
                    height,
                    iso_639_1,
                    vote_average,
                    vote_count,
                    width
                )
            )
        }
    }

    return com.movies.domain.MovieImages(
        id,
        backdrops,
        posters
    )
}