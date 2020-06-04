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
import com.bootcamp.kotlin.ui.search.SearchFragment
import com.bootcamp.kotlin.ui.search.SearchViewModel
import com.bootcamp.kotlin.ui.splash.SplashActivity
import com.bootcamp.kotlin.ui.splash.SplashViewModel
import com.bootcamp.kotlin.data.source.PreferenceDataSource
import com.movies.data.repository.*
import com.movies.data.source.DataBaseDataSource
import com.movies.data.source.RemoteDataSource
import com.movies.data.source.SharedPreferencesDataSource
import com.movies.interactor.*
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
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
    single<CoroutineDispatcher> { Dispatchers.Main }
    factory<SharedPreferencesDataSource> { PreferenceDataSource(get()) }

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
    factory<SharedPreferencesRepository> {
        SharedPreferencesRepositoryImpl(
            sharedPreferencesDataSource = get()
        )
    }

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

    scope(named<SplashActivity>()) {
        viewModel { SplashViewModel(get(), get()) }
        scoped { GetPreferencesExists(get()) }
    }

    scope(named<HomeFragment>()) {
        viewModel {
            MoviesViewModel(
                uiDispatcher = get(),
                getPopularMovies = get()
            )
        }
        scoped { GetPopularMovies(movieRepository = get()) }
    }
    scope(named<FavoriteFragment>()) {
        viewModel {
            FavoriteViewModel(
                uiDispatcher = get(),
                getFavoriteMovies = get()
            )
        }
        scoped { GetFavoriteMovies(movieRepository = get()) }
    }

    scope(named<MovieDetailFragment>()) {
        viewModel { (movieId: Int) ->
            MovieDetailViewModel(
                uiDispatcher = get(),
                getMovieDetail = get(),
                getMovieDetailImages = get(),
                movieId = movieId
            )
        }
        scoped { GetMovieDetail(movieRepository = get()) }
        scoped { GetMovieDetailImages(movieRepository = get()) }
    }

    scope (named<SearchFragment>()){
        viewModel { SearchViewModel(get(),get(),get())}
        scoped { GetSearchAutocomplete(get())}
        scoped { GetSearchMovies(movieRepository = get())}
    }
}