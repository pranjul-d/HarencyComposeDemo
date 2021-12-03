package com.macamps.harencycomposedemo.ui.auth.viewModel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.*
import com.macamps.harencycomposedemo.data.CountriesItem
import com.macamps.harencycomposedemo.data.SuccessModel
import com.macamps.harencycomposedemo.data.UserRegisterModel
import com.macamps.harencycomposedemo.di.IoDispatcher
import com.macamps.harencycomposedemo.ui.auth.login.repo.AuthRepository
import com.macamps.harencycomposedemo.utils.ApiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class AuthSharedViewModel @Inject constructor(
    private val repository: AuthRepository,
    @IoDispatcher val ioDispatcher: CoroutineDispatcher
) :
    ViewModel(), DefaultLifecycleObserver {

    var mutableCountryCode = MutableLiveData<CountriesItem>()
    val countryLiveData: LiveData<CountriesItem> = mutableCountryCode


    /** Migrated from LiveData to Kotlin StateFlow **/

    private var loginMutableStateFlow =
        MutableStateFlow<ApiState<Response<UserRegisterModel>>?>(ApiState.Empty)

    val loginApiStateFlow: StateFlow<ApiState<Response<UserRegisterModel>>?> =
        loginMutableStateFlow.asStateFlow()


    var isVisible = mutableStateOf(false)
    private var mSendOtpMutableStateFlow =
        MutableStateFlow<ApiState<Response<SuccessModel>>?>(ApiState.Empty)
    val mSendStateFlow = mSendOtpMutableStateFlow.asStateFlow()

    private val isLoadingMutableStateFlow = MutableStateFlow(false)
    val isLoading = isLoadingMutableStateFlow.asStateFlow()

    suspend fun sendOtp(
        countryCode: String?,
        phoneNumber: String,
        type: String
    ) {
        mSendOtpMutableStateFlow.value = ApiState.Loading

        isLoadingMutableStateFlow.value = true

        withContext(ioDispatcher) {
            mSendOtpMutableStateFlow.value =
                repository.forgotPassword(countryCode, phoneNumber, type)
        }

        withContext(ioDispatcher) { isLoadingMutableStateFlow.value = false }

    }

    suspend fun login(loginRequest: HashMap<String, String?>) {
        loginMutableStateFlow.value = ApiState.Loading
        isLoadingMutableStateFlow.value = true

        withContext(ioDispatcher) { loginMutableStateFlow.value = repository.login(loginRequest) }
        withContext(ioDispatcher) { isLoadingMutableStateFlow.value = false }

    }
}