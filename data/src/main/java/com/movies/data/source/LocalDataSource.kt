package com.movies.data.source

import com.movies.data.common.Resource
import com.movies.domain.InputSearch
import com.movies.domain.PopularMovie

interface LocalDataSource {
    fun popularMovies(): Resource<List<PopularMovie>>
    suspend fun insertInputSearch(inputSearch: InputSearch)
    suspend fun getAllInputSearch():List<InputSearch>?
}