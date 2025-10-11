package com.rapid.compose.core.data.repository

import com.rapid.compose.core.model.ApiResponse
import com.rapid.compose.core.network.NetworkError
import com.rapid.compose.core.network.Resource
import com.rapid.compose.core.network.safeApiCall

abstract class BaseRepository {

    protected suspend fun <T> request(call: suspend () -> ApiResponse<T>): Resource<T?> {
        return request(call) { it }
    }

    protected suspend fun <T, R> request(
        call: suspend () -> ApiResponse<T>,
        mapper: (T?) -> R
    ): Resource<R> {
        return when (val result = safeApiCall(call)) {
            is Resource.Success -> mapApiResponse(result.result, mapper)
            is Resource.Error -> Resource.Error(result.error)
            Resource.Loading -> Resource.Loading
        }
    }

    private fun <T, R> mapApiResponse(
        response: ApiResponse<T>,
        mapper: (T?) -> R
    ): Resource<R> {
        return if (response.errorCode == 0) {
            Resource.Success(mapper(response.data))
        } else {
            Resource.Error(
                NetworkError(
                    code = response.errorCode,
                    message = response.errorMsg.ifBlank { "请求失败" }
                )
            )
        }
    }
}
