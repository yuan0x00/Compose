package com.rapid.compose.core.model

import kotlinx.serialization.Serializable

@Serializable
data class UserInfoBean(
    val userInfo: LoginBean = LoginBean(),
    val coinInfo: CoinBean = CoinBean(),
    val collectArticleInfo: CollectArticleInfoBean = CollectArticleInfoBean()
)
