package com.macamps.harencycomposedemo.data

import com.google.gson.annotations.SerializedName

data class SuccessModel(

    @field:SerializedName("success")
    val success: Boolean? = null,

    @field:SerializedName("otp")
    val otp: Int? = null,

    @field:SerializedName("message")
    val message: String? = null,

    @field:SerializedName("auth_token")
    val auth_token: String? = null,

    @field:SerializedName("file_name")
    val file_name: String? = null
)