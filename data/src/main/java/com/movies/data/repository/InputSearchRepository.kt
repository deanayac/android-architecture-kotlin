package com.movies.data.repository

import com.movies.domain.InputSearch

interface InputSearchRepository {
    suspend fun getAllInputSearch():List<InputSearch>?
}