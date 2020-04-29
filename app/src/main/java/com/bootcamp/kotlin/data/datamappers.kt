package com.bootcamp.kotlin.data

import com.bootcamp.kotlin.data.database.entity.Genre as DataBaseGenre
import com.bootcamp.kotlin.data.database.entity.Movie as DataBaseMovie
import com.bootcamp.kotlin.data.server.FavoriteMovie as ServerFavoriteMovie
import com.bootcamp.kotlin.data.server.MovieDbResult
import com.movies.domain.Backdrops
import com.movies.domain.FavoriteMovie as DomainFavoriteMovie
import com.movies.domain.Genre as DomainGenre
import com.movies.domain.Movie as DomainMovie
import com.movies.domain.MovieImages
import com.movies.domain.Posters
import com.bootcamp.kotlin.data.database.entity.InputSearch as InputSearchEntity
import com.bootcamp.kotlin.data.server.Movie as ServerMovie
import com.bootcamp.kotlin.data.server.MovieImages as ServerMovieImages
import com.movies.domain.InputSearch as InputSearchDomain

fun MovieDbResult.toDomainMovie(): DomainMovie {
    val listGenres = ArrayList<DomainGenre>()

    this.genres.forEach { (id, name) ->
        listGenres.add(DomainGenre(id, name))
    }

    return DomainMovie(
        backDropPath,
        listGenres,
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

fun ServerMovie.toDomainMovie(): DomainMovie {
    val listGenres = ArrayList<DomainGenre>()
    genreIds.map {
        listGenres.add(DomainGenre(it, ""))
    }

    return DomainMovie(
        backdropPath,
        listGenres,
        id,
        originalTitle,
        overview,
        posterPath,
        releaseDate,
        title,
        video,
        voteAverage
    )
}

fun ServerFavoriteMovie.toDomainFavoriteMovie() = DomainFavoriteMovie(
    id,
    title,
    posterPath
)

fun DomainMovie.toDataBaseMovie(): DataBaseMovie {
    val listGenres = ArrayList<DataBaseGenre>()
    genres.map {
        listGenres.add(DataBaseGenre(it.id, it.name))
    }

    return DataBaseMovie(
        id,
        title,
        backdropPath,
        listGenres,
        originalTitle,
        overview,
        posterPath,
        releaseDate,
        video,
        voteAverage
    )

}

fun DataBaseGenre.toDomainGenre() = DomainGenre(
    id,
    name
)

fun DataBaseMovie.toDomainMovie(): DomainMovie {
    val listGenres = ArrayList<DomainGenre>()
    genres?.map {
        listGenres.add(DomainGenre(it.id, it.name))
    }

    return DomainMovie(
        backdropPath,
        listGenres,
        id,
        originalTitle,
        overview,
        posterPath,
        releaseDate,
        title,
        video,
        voteAverage
    )
}

fun InputSearchDomain.toDomainToEntity(): InputSearchEntity = InputSearchEntity(0, description)

fun InputSearchEntity.toDomainEntityIn(): InputSearchDomain {
    return InputSearchDomain(description)
}

fun ServerMovieImages.toDomainMovieImages(): MovieImages {
    val backdrops = ArrayList<com.movies.domain.Backdrops>()
    val posters = ArrayList<com.movies.domain.Posters>()

    this.backdrops.forEach { backdrop ->
        with(backdrop) {
            backdrops.add(
                Backdrops(
                    aspectRatio,
                    filePath,
                    height,
                    iso_639_1,
                    voteAverage,
                    voteCount,
                    width
                )
            )
        }
    }

    this.posters.forEach { poster ->
        with(poster) {
            posters.add(
                Posters(
                    aspectRatio,
                    filePath,
                    height,
                    iso_639_1,
                    voteAverage,
                    voteCount,
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