package com.macamps.harencycomposedemo.utils


sealed class State<out T> {
    data class Success<out T>(val data: T?) : State<T>()
    data class Failure(val message: String) : State<Nothing>()
    object Loading : State<Nothing>()
    object Empty : State<Nothing>()
}