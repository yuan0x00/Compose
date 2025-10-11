package com.rapid.compose.core.network

import kotlinx.serialization.SerializationException
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

suspend fun <T> safeApiCall(apiCall: suspend () -> T): Resource<T> {
    return try {
        Resource.Success(apiCall())
    } catch (e: Exception) {
        Resource.Error(e.toNetworkError())
    }
}

/**
 * 将异常转换为 NetworkError
 */
fun Exception.toNetworkError(): NetworkError {
    return when (this) {
        is HttpException -> NetworkError(
            code = code(),
            message = message(),
            isNetworkIssue = false,
            cause = this
        )
        is UnknownHostException -> NetworkError(
            code = -1,
            message = "网络连接失败，请检查网络",
            isNetworkIssue = true,
            cause = this
        )
        is SocketTimeoutException -> NetworkError(
            code = -1,
            message = "网络请求超时",
            isNetworkIssue = true,
            cause = this
        )
        is IOException -> NetworkError(
            code = -1,
            message = "网络异常: ${message}",
            isNetworkIssue = true,
            cause = this
        )
        is SerializationException -> NetworkError(
            code = -1,
            message = "数据解析失败: ${message}",
            isNetworkIssue = false,
            cause = this
        )
        else -> NetworkError(
            code = -1,
            message = message ?: "未知错误",
            isNetworkIssue = false,
            cause = this
        )
    }
}