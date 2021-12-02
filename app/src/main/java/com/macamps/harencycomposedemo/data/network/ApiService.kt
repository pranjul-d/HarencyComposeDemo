package com.macamps.harencycomposedemo.data.network

import com.macamps.harencycomposedemo.data.Posts
import com.macamps.harencycomposedemo.data.SuccessModel
import com.macamps.harencycomposedemo.data.UserRegisterModel
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    companion object{
       const val BASE_URL = "https://jsonplaceholder.typicode.com/"
       const val debugURL = "https://harency.softradixtechnologies.com/api/"
    }

    @GET("posts")
    suspend fun getPosts() : List<Posts>

    @POST("auth/login")
    suspend fun login(@Body hashMap: HashMap<String, String?>): Response<UserRegisterModel>

    @FormUrlEncoded
    @POST("auth/send_otp")
    suspend fun sendOtp(
        @Field("country_code") countryCode: String?,
        @Field("phone_number") phone_number: String?,
        @Field("type") type: String? //1-forgetPassword 0-register
    ): Response<SuccessModel>
}