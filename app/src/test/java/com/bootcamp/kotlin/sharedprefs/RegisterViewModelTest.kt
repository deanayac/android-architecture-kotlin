package com.bootcamp.kotlin.sharedprefs

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.bootcamp.kotlin.ui.register.RegisterUiModel
import com.bootcamp.kotlin.ui.register.RegisterViewModel
import com.movies.interactor.GetPreferencesExists
import com.movies.interactor.SetPreferencesName
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

/**
 * Created by jhon on 27/06/2020
 */

@RunWith(MockitoJUnitRunner::class)
class RegisterViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    lateinit var setPreferencesName: SetPreferencesName

    @Mock
    lateinit var getPreferencesExists: GetPreferencesExists

    @Mock
    lateinit var observer: Observer<RegisterUiModel>

    private lateinit var vm: RegisterViewModel

    @Before
    fun setUp() {
        vm = RegisterViewModel(Dispatchers.Unconfined, setPreferencesName, getPreferencesExists)
    }

    @Test
    fun `observing getPreferences if user already exists`() {
        runBlocking {
            val name = "jhon"

            whenever(setPreferencesName.invoke(name)).thenReturn(true)

            whenever(getPreferencesExists.invoke()).thenReturn(name)

            vm.model.observeForever(observer)

            verify(observer).onChanged(RegisterUiModel.Navigation)
        }
    }
}