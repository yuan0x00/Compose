package com.rapid.compose.core.network.model

import kotlinx.serialization.Serializable

@Serializable
data class BaseResponse<T>(
    val data: T? = null,
    val errorCode: Int = 0,
    val errorMsg: String? = ""
)