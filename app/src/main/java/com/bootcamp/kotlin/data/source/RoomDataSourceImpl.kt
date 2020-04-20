package com.bootcamp.kotlin.data.source

import android.util.Log
import com.bootcamp.kotlin.data.database.AppDatabase
import com.bootcamp.kotlin.data.provider.ApplicationProvider
import com.bootcamp.kotlin.data.toDomainToEntity
import com.bootcamp.kotlin.data.toEntityInDomain
import com.movies.data.common.Resource
import com.movies.domain.InputSearch
import com.movies.domain.PopularMovie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RoomDataSourceImpl: RoomDataSource {
    override fun popularMovies(): Resource<List<PopularMovie>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun insertInputSearch(inputSearch: InputSearch) = withContext(Dispatchers.IO){
        Log.d("INPUT0","INPUT0")
        ApplicationProvider.listen { application ->
            val database = AppDatabase.getDatabase(application)
            database.searchDao().insertInputSearch(inputSearch.toDomainToEntity())
            Log.d("INPUT","INPUT")
        }
    }

    override suspend fun getAllInputSearch(): List<InputSearch>?  = withContext(Dispatchers.IO){
        var listInput: List<InputSearch>? = null
        ApplicationProvider.listen { application ->
            val database = AppDatabase.getDatabase(application)
            listInput =  database.searchDao().getAll().toEntityInDomain()
        }
        listInput
    }

}