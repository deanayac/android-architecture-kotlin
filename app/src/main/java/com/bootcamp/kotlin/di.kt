package com.bootcamp.kotlin

import android.app.Application
import com.bootcamp.kotlin.ui.search.SearchFragment
import com.bootcamp.kotlin.ui.search.SearchViewModel
import com.movies.data.repository.InputSearchRepository
import com.movies.data.repository.InputSearchRepositoryImpl
import com.movies.data.repository.MovieRepositoryImpl
import com.movies.interactor.GetSearchAutocomplete
import com.movies.interactor.GetSearchMovies
import org.koin.android.ext.koin.androidApplication
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
}

val dataModule = module {
        factory { InputSearchRepositoryImpl(get()) }
        factory { MovieRepositoryImpl(get(),get()) }
}

private val scopesModule = module {
    scope(named<SearchFragment>()){
        viewModel { SearchViewModel(get(),get(),get()) }
        scoped{GetSearchAutocomplete(get())}
        scoped{GetSearchMovies(get())}
    }
}