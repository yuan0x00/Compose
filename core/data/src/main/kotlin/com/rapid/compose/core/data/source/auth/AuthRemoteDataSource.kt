package com.rapid.compose.core.data.source.auth

import com.rapid.compose.core.model.ApiResponse
import com.rapid.compose.core.model.LoginBean
import com.rapid.compose.core.model.RegisterBean

interface AuthRemoteDataSource {
    suspend fun register(username: String, password: String, rePassword: String): ApiResponse<RegisterBean>
    suspend fun login(username: String, password: String): ApiResponse<LoginBean>
    suspend fun logout(): ApiResponse<String>
}
