package com.macamps.harencycomposedemo.data


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class CountriesItem(
    @SerializedName("CodeTelePhone")
    val codeTelePhone: String,
    @SerializedName("ic_code")
    val icCode: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("Name")
    val name: String
):Parcelable