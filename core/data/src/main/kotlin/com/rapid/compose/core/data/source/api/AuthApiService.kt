package com.rapid.compose.core.data.source.api

import com.rapid.compose.core.model.ApiResponse
import com.rapid.compose.core.model.LoginBean
import com.rapid.compose.core.model.RegisterBean
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

/**
 * 认证相关接口。
 */
interface AuthApiService {

    @FormUrlEncoded
    @POST("/user/register")
    suspend fun register(
        @Field("username") username: String,
        @Field("password") password: String,
        @Field("repassword") rePassword: String
    ): ApiResponse<RegisterBean>

    @FormUrlEncoded
    @POST("/user/login")
    suspend fun login(
        @Field("username") username: String,
        @Field("password") password: String
    ): ApiResponse<LoginBean>

    @GET("/user/logout/json")
    suspend fun logout(): ApiResponse<String>
}
