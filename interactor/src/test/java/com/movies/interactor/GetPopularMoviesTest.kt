package com.movies.interactor

import com.movies.data.repository.MovieRepository
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import pe.gob.msb.gp.testshared.mockedMovie

@RunWith(MockitoJUnitRunner::class)
class GetPopularMoviesTest {

    @Mock
    lateinit var movieRepository: MovieRepository

    lateinit var getPopularMovies: GetPopularMovies

    @Before
    fun setUp() {
        getPopularMovies = GetPopularMovies(movieRepository)
    }

    @Test
    fun `invoke calls movie repository`() {
        runBlocking {
            val movies = listOf(mockedMovie.copy(id = 10))
            whenever(getPopularMovies.invoke()).thenReturn(movies)

            val result = getPopularMovies.invoke()

            assertEquals(movies,  result)
        }
    }

}