package com.bootcamp.kotlin.ui.movies

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.movies.interactor.GetPopularMovies
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import pe.gob.msb.gp.testshared.mockedMovie

@RunWith(MockitoJUnitRunner::class)
class MoviesViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    lateinit var popularMovies: GetPopularMovies

    @Mock
    lateinit var observer: Observer<MovieUiModel>

    private lateinit var moviesViewModel: MoviesViewModel

    @Before
    fun setUp() {
        moviesViewModel = MoviesViewModel(Dispatchers.Unconfined, popularMovies)
    }

    @Test
    fun `popularMovies is called`() = runBlocking {
        val movies = listOf(mockedMovie.copy(id = 100))

        whenever(popularMovies.invoke()).thenReturn(movies)
        moviesViewModel.model.observeForever(observer)

        verify(observer).onChanged(MovieUiModel.Content(movies))
    }
}