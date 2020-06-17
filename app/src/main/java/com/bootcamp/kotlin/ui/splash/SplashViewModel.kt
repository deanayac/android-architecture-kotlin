package com.bootcamp.kotlin.ui.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bootcamp.kotlin.ui.common.ScopedViewModel
import com.movies.interactor.GetPreferencesExists
import com.movies.interactor.GetPreferencesName
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

/**
 * Created by jhon on 6/04/2020
 */
class SplashViewModel(
    uiDispatcher: CoroutineDispatcher,
    private val getPreferencesExists: GetPreferencesExists
) : ScopedViewModel(uiDispatcher) {

    private val _model = MutableLiveData<UiModel>()
    val model: LiveData<UiModel>
        get() {
            if (_model.value == null) checkPreferences()
            return _model
        }

    sealed class UiModel {
        data class CheckPreferences(val getSharedPreferences: String) : UiModel()
    }

    private fun checkPreferences() = launch {
        _model.value = UiModel.CheckPreferences(getPreferencesExists.invoke())
    }
}