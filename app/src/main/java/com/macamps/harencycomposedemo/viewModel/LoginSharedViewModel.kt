package com.macamps.harencycomposedemo.viewModel

import androidx.lifecycle.*
import com.macamps.harencycomposedemo.data.CountriesItem
import com.macamps.harencycomposedemo.data.UserRegisterModel
import com.macamps.harencycomposedemo.data.network.ApiService
import com.macamps.harencycomposedemo.utils.State
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class LoginSharedViewModel @Inject constructor(private val api: ApiService): ViewModel(), DefaultLifecycleObserver {

    var mutableCountryCode = MutableLiveData<CountriesItem>()
    val countryLiveData: LiveData<CountriesItem> = mutableCountryCode

    private var mutableLogin = MutableLiveData<State<UserRegisterModel>?>()
    var loginLiveData: LiveData<State<UserRegisterModel>?> = mutableLogin


    fun login(loginRequest: HashMap<String, String?>){
        loginLiveData = liveData {
            emit(State.Loading)
            val response = api.login(loginRequest)
            try{

                when(response.code()) {
                    200 -> {
                        emit(State.Success(response))
                    }
                    404->{

                    }
                }
            }catch (e:Exception){
                emit(State.Failure("Something went wrong!"))
            }
        }

    }

}