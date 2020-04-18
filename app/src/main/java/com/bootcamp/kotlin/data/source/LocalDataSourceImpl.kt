package com.bootcamp.kotlin.data.source

import com.movies.data.common.Resource
import com.movies.data.source.LocalDataSource

import com.movies.domain.PopularMovie

class LocalDataSourceImpl(
) : LocalDataSource {
    override fun popularMovies(): Resource<List<PopularMovie>> {
        return Resource.loading(emptyList())
    }
}