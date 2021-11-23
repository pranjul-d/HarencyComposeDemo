package com.macamps.harencycomposedemo.ui.login.repo


import com.macamps.harencycomposedemo.data.UserRegisterModel
import com.macamps.harencycomposedemo.data.network.ApiService
import com.macamps.harencycomposedemo.utils.State
import retrofit2.Response
import javax.inject.Inject
import kotlin.Exception

class LoginRepository @Inject constructor(private val api: ApiService) {

suspend fun login(loginRequest: HashMap<String, String?>): State<Response<UserRegisterModel>> {

    val response = try{
        api.login(loginRequest)
    } catch (e: Exception){
        return State.Error(Throwable("An unknown error occured."))
    }
    return State.Success(response)
}

}