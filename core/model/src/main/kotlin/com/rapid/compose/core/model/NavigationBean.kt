package com.rapid.compose.core.model

import kotlinx.serialization.Serializable

@Serializable
data class NavigationBean(
    val cid: Int = 0,
    val name: String? = "",
    val articles: List<ArticleListBean.Data> = emptyList()
)
