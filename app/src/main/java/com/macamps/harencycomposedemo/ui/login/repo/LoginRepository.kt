package com.macamps.harencycomposedemo.ui.login.repo

import com.macamps.harencycomposedemo.data.network.ApiService
import com.macamps.harencycomposedemo.utils.toResultFlow
import javax.inject.Inject

class LoginRepository @Inject constructor(private val api: ApiService) {
    fun login(loginRequest: HashMap<String, String?>) = toResultFlow {
        api.login(loginRequest)
    }
}