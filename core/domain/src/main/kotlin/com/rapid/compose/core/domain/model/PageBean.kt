package com.rapid.compose.core.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class PageBean<T>(
    val curPage: Int = 0,
    val datas: List<T> = emptyList(),
    val offset: Int = 0,
    val isOver: Boolean = false,
    val pageCount: Int = 0,
    val size: Int = 0,
    val total: Int = 0
)
