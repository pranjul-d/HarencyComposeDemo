package com.macamps.harencycomposedemo.ui.auth.login.repo


import com.macamps.harencycomposedemo.data.SuccessModel
import com.macamps.harencycomposedemo.data.UserRegisterModel
import com.macamps.harencycomposedemo.data.network.ApiService
import com.macamps.harencycomposedemo.utils.ApiState
import retrofit2.Response
import javax.inject.Inject
import kotlin.Exception

class AuthRepository @Inject constructor(private val api: ApiService) {

    suspend fun login(loginRequest: HashMap<String, String?>): ApiState<Response<UserRegisterModel>> {

        val response = try {
            api.login(loginRequest)
        } catch (e: Exception) {
            return ApiState.Error(Throwable("An unknown error occurred!"))
        }
        return ApiState.Success(response)
    }

    suspend fun forgotPassword(
        countryCode: String?,
        phoneNumber: String,
        type: String
    ): ApiState<Response<SuccessModel>> {

        val response = try {
            api.sendOtp(countryCode, phoneNumber, type)
        } catch (e: Exception) {
            return ApiState.Error(Throwable("An unknown error occurred!"))
        }
        return ApiState.Success(response)
    }

}