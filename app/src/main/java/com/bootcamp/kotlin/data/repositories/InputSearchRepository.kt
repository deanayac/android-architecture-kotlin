package com.bootcamp.kotlin.data.repositories

import com.movies.data.common.Resource
import com.movies.domain.InputSearch

interface InputSearchRepository {
    suspend fun insertInputSearch(input:InputSearch):Resource<Void>
    suspend fun getAllInputSearch():Resource<List<InputSearch>>
}