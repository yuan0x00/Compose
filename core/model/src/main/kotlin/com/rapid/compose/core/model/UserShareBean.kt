package com.rapid.compose.core.model

import kotlinx.serialization.Serializable

@Serializable
data class UserShareBean(
    val coinInfo: CoinBean = CoinBean(),
    val shareArticles: ArticleListBean = ArticleListBean()
)
