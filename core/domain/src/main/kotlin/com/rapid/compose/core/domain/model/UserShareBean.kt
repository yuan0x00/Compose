package com.rapid.compose.core.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class UserShareBean(
    val coinInfo: CoinBean = CoinBean(),
    val shareArticles: ArticleListBean = ArticleListBean()
)
