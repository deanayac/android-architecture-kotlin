package com.bootcamp.kotlin.ui.register

/**
 * Created by jhon on 6/04/2020
 */
sealed class RegisterUiModel {
    object Loading : RegisterUiModel()
    object Navigation : RegisterUiModel()
    data class Content(val userName: Boolean) : RegisterUiModel()
    data class CheckUser(val getPreferencesExists: String) : RegisterUiModel()
}