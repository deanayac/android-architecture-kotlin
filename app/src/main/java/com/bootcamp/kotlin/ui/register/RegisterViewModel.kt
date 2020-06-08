package com.bootcamp.kotlin.ui.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bootcamp.kotlin.ui.common.ScopeViewModel
import com.movies.interactor.GetPreferencesExists
import com.movies.interactor.GetPreferencesName
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

/**
 * Created by jhon on 6/04/2020
 */
class RegisterViewModel(
    uiDispatcher: CoroutineDispatcher,
    private val getPreferencesName: GetPreferencesName,
    private val getPreferencesExists: GetPreferencesExists
) : ScopeViewModel(uiDispatcher) {

    init {
        createScope()
    }

    private val _model = MutableLiveData<RegisterUiModel>()
    val model: LiveData<RegisterUiModel>
        get() {
            if (_model.value == null) navigateToHome()
                return _model
        }

    fun saveUserName(userName: String) = launch {
        _model.value = RegisterUiModel.Content(getPreferencesName.invoke(userName))
    }

    private fun navigateToHome() = launch{
        _model.value = RegisterUiModel.Loading
        _model.value = RegisterUiModel.CheckUser(getPreferencesExists.invoke())
        _model.value = RegisterUiModel.Navigation
    }
}