package com.bootcamp.kotlin

import FavoriteFragment
import FavoriteViewModel
import android.app.Application
import com.bootcamp.kotlin.data.database.AppDatabase
import com.bootcamp.kotlin.data.server.ApiClient
import com.bootcamp.kotlin.data.source.RetrofitDataSource
import com.bootcamp.kotlin.data.source.RoomDataSource
import com.bootcamp.kotlin.ui.moviedetail.MovieDetailFragment
import com.bootcamp.kotlin.ui.moviedetail.MovieDetailViewModel
import com.bootcamp.kotlin.ui.movies.HomeFragment
import com.bootcamp.kotlin.ui.movies.MoviesViewModel
import com.bootcamp.kotlin.ui.search.SearchContract
import com.bootcamp.kotlin.ui.search.SearchPresenter
import com.movies.data.repository.InputSearchRepository
import com.movies.data.repository.InputSearchRepositoryImpl
import com.movies.data.repository.MovieRepository
import com.movies.data.repository.MovieRepositoryImpl
import com.movies.data.source.DataBaseDataSource
import com.movies.data.source.RemoteDataSource
import com.movies.interactor.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.qualifier.named
import org.koin.dsl.module

fun Application.initDI() {
    startKoin {
        androidLogger()
        androidContext(this@initDI)
        modules(listOf(appModule, dataModule, scopesModule))
    }
}

private val appModule = module {
    single(named("apiKey")) { "5de961ca47ac20a3689205becc3c3b20" }
    single { ApiClient.movieDbServices }
    single { AppDatabase.getInstance(get()) }

    factory<DataBaseDataSource> {
        RoomDataSource(appDatabase = get())
    }

    factory<RemoteDataSource> {
        RetrofitDataSource(
            movieDbServices = get(),
            apiKey = get(named("apiKey"))
        )
    }
}

val dataModule = module {
    factory<InputSearchRepository> {
        InputSearchRepositoryImpl(
            dataBaseDataSource = get()
        )
    }

    factory<MovieRepository> {
        MovieRepositoryImpl(
            dataBaseDataSource = get(),
            remoteDataSource = get()
        )
    }
}

private val scopesModule = module {
    scope(named<HomeFragment>()) {
        viewModel { MoviesViewModel(getPopularMovies = get()) }
        scoped { GetPopularMovies(movieRepository = get()) }
    }
    scope(named<FavoriteFragment>()) {
        viewModel { FavoriteViewModel(getFavoriteMovies = get()) }
        scoped { GetFavoriteMovies(movieRepository = get()) }
    }
    scope(named<MovieDetailFragment>()) {
        viewModel { (movieId: Int) ->
            MovieDetailViewModel(
                getMovieDetail = get(),
                getMovieDetailImages = get(),
                movieId = movieId
            )
        }
        scoped { GetMovieDetail(movieRepository = get()) }
        scoped { GetMovieDetailImages(movieRepository = get()) }
    }

    factory { GetSearchAutocomplete(inputSearchRepository = get()) }
    factory { GetSearchMovies(movieRepository = get()) }
    factory<SearchContract.Presenter> { (view: SearchContract.View) ->
        SearchPresenter(
            view = view,
            getSearchAutocomplete = get(),
            getSearchMovies = get()
        )
    }
}