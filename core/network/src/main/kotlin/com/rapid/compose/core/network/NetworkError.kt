package com.rapid.compose.core.network

data class NetworkError(
    val code: Int = -1,
    val message: String = "Unknown error",
    val isNetworkIssue: Boolean = false,
    val cause: Throwable? = null
)