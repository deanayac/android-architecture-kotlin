package com.movies.data.repository

import com.movies.data.common.Resource
import com.movies.domain.MovieImages

/**
 * Created by jhon on 20/04/2020
 */
interface MovieImageDetailRepository {
    suspend fun getMovieImageDetail(id: Int) : Resource<MovieImages>
}