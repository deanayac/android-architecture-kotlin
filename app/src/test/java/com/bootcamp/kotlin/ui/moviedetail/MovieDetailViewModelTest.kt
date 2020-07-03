package com.bootcamp.kotlin.ui.moviedetail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.movies.data.common.Resource
import com.movies.interactor.GetMovieDetail
import com.movies.interactor.GetMovieDetailImages
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
import pe.gob.msb.gp.testshared.mockedMovieImages

@RunWith(MockitoJUnitRunner::class)
class MovieDetailViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    lateinit var getMovieDetail: GetMovieDetail

    @Mock
    lateinit var getMovieDetailImages: GetMovieDetailImages

    @Mock
    lateinit var observer: Observer<MovieDetailUiModel>

    private lateinit var movieDetailViewModel: MovieDetailViewModel

    @Before
    fun setUp() {
        movieDetailViewModel =
            MovieDetailViewModel(
                Dispatchers.Unconfined,
                getMovieDetail,
                getMovieDetailImages,
                1)
    }

    @Test
    fun `load data`() = runBlocking {
        val movie = mockedMovie.copy(id = 1)
        val movieImages = mockedMovieImages.copy(id = 1)

        whenever(getMovieDetail.invoke(1)).thenReturn(Resource.success(movie))
        whenever(getMovieDetailImages.invoke(1)).thenReturn(Resource.success(movieImages))
        movieDetailViewModel.model.observeForever(observer)

        verify(observer).onChanged(MovieDetailUiModel.Posters(movieImages))
        verify(observer).onChanged(MovieDetailUiModel.Header(movie))

    }
}