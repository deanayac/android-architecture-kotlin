package com.movies.data.repository

import com.movies.data.source.LocalDataSource
import com.movies.domain.InputSearch

class InputSearchRepositoryImpl(private val localDataSource: LocalDataSource):InputSearchRepository{
    override suspend fun getAllInputSearch():List<InputSearch>? {
        return localDataSource.getAllInputSearch()
    }
}