package com.bootcamp.kotlin.ui.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bootcamp.kotlin.ui.common.ScopedViewModel
import com.bootcamp.kotlin.util.AccountRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

/**
 * Created by jhon on 6/04/2020
 */
class SplashViewModel(
    uiDispatcher: CoroutineDispatcher,
    private val repository: AccountRepository
): ScopedViewModel(uiDispatcher) {

    private val _model = MutableLiveData<UiModel>()
    val model: LiveData<UiModel>
    get() {
        if (_model.value == null) sleepScreen()
        return _model
    }

    sealed class UiModel {
        object SleepScreen : UiModel()
        object Navigation : UiModel()
    }

    init {
        initScope()
    }

    fun sleepScreen() {
        launch {
            repository.checkIfUserExists().apply {
                if (isEmpty()) {
                    _model.value = UiModel.Navigation
                    return@apply
                }
                _model.value = UiModel.SleepScreen
            }
        }
    }

    override fun onCleared() {
        destroyScope()
        super.onCleared()
    }
}