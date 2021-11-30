package com.macamps.harencycomposedemo.ui.auth.login.repo


import com.macamps.harencycomposedemo.data.UserRegisterModel
import com.macamps.harencycomposedemo.data.network.ApiService
import com.macamps.harencycomposedemo.utils.ApiState
import retrofit2.Response
import javax.inject.Inject
import kotlin.Exception

class LoginRepository @Inject constructor(private val api: ApiService) {

suspend fun login(loginRequest: HashMap<String, String?>): ApiState<Response<UserRegisterModel>> {

    val response = try{
        api.login(loginRequest)
    } catch (e: Exception){
        return ApiState.Error(Throwable("An unknown error occured."))
    }
    return ApiState.Success(response)
}

}