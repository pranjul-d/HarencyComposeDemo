package com.macamps.harencycomposedemo.data.network

import com.macamps.harencycomposedemo.data.Posts
import com.macamps.harencycomposedemo.data.UserRegisterModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    companion object{
       const val BASE_URL = "https://jsonplaceholder.typicode.com/"
       const val debugURL = "https://harency.softradixtechnologies.com/api/"
    }

    @GET("posts")
    suspend fun getPosts() : List<Posts>

    @POST("auth/login")
    suspend fun login(@Body hashMap: HashMap<String, String?>): Response<UserRegisterModel>
}