package com.movies.data.repository

import com.movies.data.source.DataBaseDataSource
import com.movies.domain.InputSearch

class InputSearchRepositoryImpl(private val dataBaseDataSource: DataBaseDataSource):InputSearchRepository{

    override suspend fun getAllInputSearch():List<InputSearch>? {
        return dataBaseDataSource.getAllInputSearch()
    }
}