package com.movies.interactor

import com.movies.data.repository.InputSearchRepository
import com.movies.domain.InputSearch

class GetSearchAutocomplete(private val inputSearchRepository: InputSearchRepository ) {
    suspend fun invoke(): List<InputSearch>? = inputSearchRepository.getAllInputSearch()
}