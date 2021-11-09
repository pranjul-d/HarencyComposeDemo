package com.macamps.harencycomposedemo.data

import com.google.gson.annotations.SerializedName

data class UserRegisterModel(

    @SerializedName("success")
    val success: Boolean? = null,

    @SerializedName("message")
    val message: String? = null,

    @SerializedName("user")
    val user: User? = null,

    @SerializedName("token")
    val token: String? = null
)

data class User(

    @SerializedName("image")
    val image: String? = null,

    @SerializedName("created_at")
    val createdAt: String? = null,

    @SerializedName("otp")
    val otp: Any? = null,

    @SerializedName("email_verified_at")
    val emailVerifiedAt: Any? = null,

    @SerializedName("social_type")
    val social_type: Any? = null,

    @SerializedName("deleted_at")
    val deletedAt: Any? = null,

    @SerializedName("push_notification")
    val pushNotification: String? = null,

    @SerializedName("country_code")
    val countryCode: String? = null,

    @SerializedName("profile_status")
    val profileStatus: String? = null,

    @SerializedName("updated_at")
    val updatedAt: String? = null,

    @SerializedName("name")
    val name: String? = null,

    @SerializedName("phone_number")
    val phoneNumber: String? = null,

    @SerializedName("id")
    val id: Int? = null,

    @SerializedName("email")
    val email: String? = null,

    @SerializedName("is_pasword_generated")
    val is_pasword_generated: String? = null,

    @SerializedName("card")
    val card: ArrayList<CardItem>? = null


)
