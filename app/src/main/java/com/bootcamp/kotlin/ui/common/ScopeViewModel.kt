package com.bootcamp.kotlin.ui.common

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineDispatcher

abstract class ScopeViewModel(
    uiDispatcher: CoroutineDispatcher
) : ViewModel(), Scope by Scope.Impl(uiDispatcher) {
    init {
        createScope()
    }

    override fun onCleared() {
        destroyScope()
        super.onCleared()
    }
}