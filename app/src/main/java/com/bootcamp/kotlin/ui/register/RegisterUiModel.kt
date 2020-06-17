package com.bootcamp.kotlin.ui.register

/**
 * Created by jhon on 6/04/2020
 */
sealed class RegisterUiModel {
    object Loading : RegisterUiModel()
    object Navigation : RegisterUiModel()
    class Content(val userName: Boolean) : RegisterUiModel()
    class CheckUser(val getPreferencesExists: String) : RegisterUiModel()
}