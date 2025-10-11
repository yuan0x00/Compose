package com.rapid.compose.core.model

import kotlinx.serialization.Serializable

@Serializable
data class ApiResponse<T>(
    val data: T? = null,
    val errorCode: Int = 0,
    val errorMsg: String = ""
)
