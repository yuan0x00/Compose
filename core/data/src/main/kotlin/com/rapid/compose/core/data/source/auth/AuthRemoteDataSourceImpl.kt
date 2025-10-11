package com.rapid.compose.core.data.source.auth

import com.rapid.compose.core.data.source.api.AuthApiService
import com.rapid.compose.core.model.ApiResponse
import com.rapid.compose.core.model.LoginBean
import com.rapid.compose.core.model.RegisterBean
import com.rapid.compose.core.network.NetworkClient

class AuthRemoteDataSourceImpl : AuthRemoteDataSource {

    private val apiService: AuthApiService by lazy { NetworkClient.create() }

    override suspend fun register(
        username: String,
        password: String,
        rePassword: String
    ): ApiResponse<RegisterBean> = apiService.register(username, password, rePassword)

    override suspend fun login(username: String, password: String): ApiResponse<LoginBean> =
        apiService.login(username, password)

    override suspend fun logout(): ApiResponse<String> = apiService.logout()
}
