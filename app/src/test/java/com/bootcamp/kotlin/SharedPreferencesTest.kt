package com.bootcamp.kotlin

import com.movies.data.repository.SharedPreferencesRepositoryImpl
import com.movies.data.source.SharedPreferencesDataSource
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

/**
 * Created by jhon on 20/06/2020
 */

@RunWith(MockitoJUnitRunner::class)
class SharedPreferencesTest {

    @Mock
    lateinit var sharedPreferencesDataSource: SharedPreferencesDataSource

    lateinit var sharedPreferencesRepositoryImpl: SharedPreferencesRepositoryImpl

    @Before
    fun setup() {
        sharedPreferencesRepositoryImpl =
            SharedPreferencesRepositoryImpl(sharedPreferencesDataSource)
    }

    @Test
    fun `getSharedPreferences save user register, login information`() {
        runBlocking {
            whenever(sharedPreferencesDataSource.saveUserName(any())).thenReturn(false)

            sharedPreferencesRepositoryImpl.checkIfUserExists()

            verify(sharedPreferencesDataSource).checkIfUserExists()
        }
    }

    @Test
    fun `getSharedPreferences validate user name`() {
        runBlocking {
            val name = "jhon"

            whenever(sharedPreferencesDataSource.checkIfUserExists()).thenReturn(name)

            val result = sharedPreferencesRepositoryImpl.checkIfUserExists()

            assertEquals(name, result)
        }
    }
}