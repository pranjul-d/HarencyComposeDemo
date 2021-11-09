package com.macamps.harencycomposedemo.ui.login.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


@HiltViewModel
class LoginViewModel constructor() :ViewModel(){
    private val _mutableLogin = MutableStateFlow<LoginUiState>(LoginUiState.Empty)
    val loginUiState : StateFlow<LoginUiState> = _mutableLogin

    fun login(username:String,password:String) = viewModelScope.launch {

    }

    sealed class LoginUiState {
        object Success : LoginUiState()
        data class Error(val message:String) : LoginUiState()
        object Loading : LoginUiState()
        object Empty : LoginUiState()
    }
}