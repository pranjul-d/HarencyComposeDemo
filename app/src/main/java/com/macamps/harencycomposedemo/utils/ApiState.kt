package com.macamps.harencycomposedemo.utils



sealed class ApiState<out T : Any> {

    data class Success<out T : Any>(val data: T) : ApiState<T>()
    data class Error(val exception: Throwable) : ApiState<Nothing>()
    object Loading : ApiState<Nothing>()
    object Empty : ApiState<Nothing>()
//    fun toData(): T? = if(this is Success) this.data else null
}
