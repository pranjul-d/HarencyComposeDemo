package com.macamps.harencycomposedemo.viewModel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.*
import com.macamps.harencycomposedemo.data.CountriesItem
import com.macamps.harencycomposedemo.data.UserRegisterModel
import com.macamps.harencycomposedemo.ui.login.repo.LoginRepository
import com.macamps.harencycomposedemo.utils.ApiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class LoginSharedViewModel @Inject constructor(private val repository: LoginRepository) :
    ViewModel(), DefaultLifecycleObserver {

    var mutableCountryCode = MutableLiveData<CountriesItem>()
    val countryLiveData: LiveData<CountriesItem> = mutableCountryCode


    /** Migrated from LiveData to Kotlin StateFlow **/

    private var loginMutableStateFlow =
        MutableStateFlow<ApiState<Response<UserRegisterModel>>?>(ApiState.Empty)

    var loginApiStateFlow: StateFlow<ApiState<Response<UserRegisterModel>>?> =
        loginMutableStateFlow.asStateFlow()


    var isVisible = mutableStateOf(false)

    suspend fun login(loginRequest: HashMap<String, String?>) {
        loginMutableStateFlow.value = ApiState.Loading
        withContext(Dispatchers.IO) {
//            delay(1000L)
            loginMutableStateFlow.value = repository.login(loginRequest)

        }
    }
}