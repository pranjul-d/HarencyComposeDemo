package com.macamps.harencycomposedemo.data

import com.google.gson.annotations.SerializedName

data class AddCardModel(

    @SerializedName("success")
    val success: Boolean? = null,

    @SerializedName("message")
    val message: String? = null,

    @SerializedName("card")
    val card: Card? = null,

    @SerializedName("user")
    val user: User? = null
)

data class Card(

    @SerializedName("image")
    val image: Any? = null,

    @SerializedName("created_at")
    val createdAt: String? = null,

    @SerializedName("otp")
    val otp: Any? = null,

    @SerializedName("email_verified_at")
    val emailVerifiedAt: Any? = null,

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

    @SerializedName("card")
    val card: List<CardItem?>? = null
)

data class CardItem(

    @SerializedName("cvv")
    val cvv: String? = null,

    @SerializedName("card_number")
    val cardNumber: String? = null,

    @SerializedName("card_holder_name")
    val cardHolderName: String? = null,

    @SerializedName("updated_at")
    val updatedAt: String? = null,

    @SerializedName("user_id")
    val userId: String? = null,

    @SerializedName("expiry_date")
    val expiryDate: String? = null,

    @SerializedName("default_card")
    val defaultCard: String? = null,

    @SerializedName("created_at")
    val createdAt: String? = null,

    @SerializedName("id")
    val id: Int? = null,

    @SerializedName("card_type")
    val cardType: String? = null,

    @SerializedName("deleted_at")
    val deletedAt: Any? = null,

    @SerializedName("selected")
    var isSelected: Boolean = false
)
