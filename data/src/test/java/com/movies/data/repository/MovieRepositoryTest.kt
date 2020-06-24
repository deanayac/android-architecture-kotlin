package com.movies.data.repository

import com.movies.data.common.Resource
import com.movies.data.source.DataBaseDataSource
import com.movies.data.source.RemoteDataSource
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.omg.CORBA.portable.ResponseHandler
import pe.gob.msb.gp.testshared.mockedMovie

@RunWith(MockitoJUnitRunner::class)
class MovieRepositoryTest {

    @Mock
    lateinit var dataBaseDataSource: DataBaseDataSource

    @Mock
    lateinit var remoteDataSource: RemoteDataSource

    lateinit var movieRepository: MovieRepository

    @Before
    fun setUp() {
        movieRepository = MovieRepositoryImpl(dataBaseDataSource, remoteDataSource)
    }

    @Test
    fun `popularMovies gets from database`() {
        runBlocking {
            val databaseMovies = listOf(mockedMovie.copy(id = 1))
            whenever(dataBaseDataSource.isEmpty()).thenReturn(false)
            whenever(dataBaseDataSource.popularMovies()).thenReturn(databaseMovies)

            val result = movieRepository.popularMovies()

            assertEquals(databaseMovies, result)
        }
    }

    @Test
    fun `popularMovies saves remote data to database`() {
        runBlocking {
            val remoteMovies = listOf(mockedMovie)
            whenever(dataBaseDataSource.isEmpty()).thenReturn(true)
            whenever(remoteDataSource.listPopularMovies()).thenReturn(Resource.success(remoteMovies))

            movieRepository.popularMovies()

            verify(dataBaseDataSource).saveMovies(remoteMovies)
        }
    }
}