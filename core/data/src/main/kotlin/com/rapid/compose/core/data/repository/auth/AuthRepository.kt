package com.rapid.compose.core.data.repository.auth

import com.rapid.compose.core.data.repository.BaseRepository
import com.rapid.compose.core.data.source.auth.AuthRemoteDataSource
import com.rapid.compose.core.model.LoginBean
import com.rapid.compose.core.model.RegisterBean
import com.rapid.compose.core.network.Resource

class AuthRepository(
    private val remoteDataSource: AuthRemoteDataSource
) : BaseRepository() {

    suspend fun register(username: String, password: String, rePassword: String): Resource<RegisterBean> =
        request({ remoteDataSource.register(username, password, rePassword) }) { it ?: RegisterBean() }

    suspend fun login(username: String, password: String): Resource<LoginBean> =
        request({ remoteDataSource.login(username, password) }) { it ?: LoginBean() }

    suspend fun logout(): Resource<String> =
        request({ remoteDataSource.logout() }) { it ?: "" }
}
