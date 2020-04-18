package com.bootcamp.kotlin.data.server

import com.bootcamp.kotlin.domain.Backdrops
import com.bootcamp.kotlin.domain.Genre
import com.bootcamp.kotlin.domain.Movie
import com.bootcamp.kotlin.domain.MovieImages
import com.bootcamp.kotlin.domain.Posters

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

fun com.bootcamp.kotlin.data.server.MovieImages.toDomainMovieImages(): MovieImages {
    val backdrops = ArrayList<Backdrops>()
    val posters = ArrayList<Posters>()

    this.backdrops.forEach { backdrop ->
        with(backdrop) {
            backdrops.add(
                Backdrops(
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
                Posters(
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

    return MovieImages(
        id,
        backdrops,
        posters
    )
}