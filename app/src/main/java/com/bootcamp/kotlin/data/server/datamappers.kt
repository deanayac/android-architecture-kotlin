package com.bootcamp.kotlin.data.server

import com.bootcamp.kotlin.domain.BackDrops
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

fun MoviesImages.toDomainMovieImages(): MovieImages {
    val backdrops = ArrayList<BackDrops>()
    val posters = ArrayList<Posters>()

    this.backdrops.forEach { (aspect_ratio, file_path, height, iso_639_1, vote_average,
                             vote_count, width) ->
        backdrops.add(BackDrops(aspect_ratio, file_path, height, iso_639_1, vote_average,
            vote_count, width))
    }

    this.posters.forEach { (aspect_ratio, file_path, height, iso_639_1, vote_average,
        vote_count, width) ->
        posters.add(Posters(aspect_ratio, file_path, height, iso_639_1, vote_average,
            vote_count, width))
    }

    return MovieImages(
        id,
        backdrops,
        posters
    )
}