package com.macamps.harencycomposedemo.viewModel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.*
import com.macamps.harencycomposedemo.data.CountriesItem
import com.macamps.harencycomposedemo.data.UserRegisterModel
import com.macamps.harencycomposedemo.ui.login.repo.LoginRepository
import com.macamps.harencycomposedemo.utils.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class LoginSharedViewModel @Inject constructor(private val repository: LoginRepository) :
    ViewModel(), DefaultLifecycleObserver {

    var mutableCountryCode = MutableLiveData<CountriesItem>()
    val countryLiveData: LiveData<CountriesItem> = mutableCountryCode
    var mutableLoginData = MutableLiveData<State<Response<UserRegisterModel>>?>()

    var loginLiveData: LiveData<State<Response<UserRegisterModel>>?> = mutableLoginData

    var isVisible = mutableStateOf(false)


    suspend fun login(loginRequest: HashMap<String, String?>) {

        mutableLoginData.value = withContext(viewModelScope.coroutineContext) {
            repository.login(loginRequest)
        }
    }
}