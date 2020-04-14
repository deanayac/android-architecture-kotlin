package com.bootcamp.kotlin.search

import com.bootcamp.kotlin.base.ApplicationMovies
import com.bootcamp.kotlin.database.InputSearch
import com.bootcamp.kotlin.database.InputSearchDAO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class InputSearchRepository(application:ApplicationMovies) {
    private val db = application.db

   suspend fun insertInputSearch(inputSearch: InputSearch)= withContext(Dispatchers.IO){
        db.inputSearch().insertInputSearch(inputSearch)
    }

    suspend fun getAllInputSearch():List<InputSearch> = withContext(Dispatchers.IO){
          db.inputSearch().getAll()
    }
}
