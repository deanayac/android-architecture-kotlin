package com.bootcamp.kotlin.ui.search

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.bootcamp.kotlin.ui.search.SearchViewModel.UiModel
import com.movies.data.common.Resource
import com.movies.interactor.GetSearchAutocomplete
import com.movies.interactor.GetSearchMovies
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
class SearchViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    lateinit var getSearchAutocomplete:GetSearchAutocomplete

    @Mock
    lateinit var getSearchMovies: GetSearchMovies

    @Mock
    lateinit var observer:Observer<UiModel>

    private lateinit var vm: SearchViewModel

    @Before
   fun setup(){
       vm = SearchViewModel(getSearchAutocomplete,getSearchMovies, Dispatchers.Unconfined)
    }

    @Test
    fun `observing LiveData launches autocomplete`(){
        vm.model.observeForever(observer)
        verify(observer).onChanged(UiModel.Autocomplete(emptyList()))
    }

    @Test
    fun `after requesting search movie, loading is shown`(){
        runBlocking {
            val movies = listOf(mockedMovie.copy(id = 10))
            whenever(getSearchMovies.invoke("")).thenReturn(Resource.success(movies))
            vm.model.observeForever(observer)
            vm.searchMovies("")
            verify(observer).onChanged(UiModel.Loading)
        }
    }

    @Test
    fun `after requesting search movie, error`(){
        runBlocking {
            val movies = listOf(mockedMovie.copy(id = 10))
            whenever(getSearchMovies.invoke("")).thenReturn(Resource.error("movies",movies))
            vm.model.observeForever(observer)
            vm.searchMovies("")
            verify(observer).onChanged(UiModel.showError)
        }
    }

}