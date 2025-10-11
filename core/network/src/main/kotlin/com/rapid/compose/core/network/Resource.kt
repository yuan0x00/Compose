package com.rapid.compose.core.network

sealed class Resource<out T> {
    data class Success<T>(val result: T) : Resource<T>()
    data class Error(val error: NetworkError) : Resource<Nothing>()
    data object Loading : Resource<Nothing>()
}