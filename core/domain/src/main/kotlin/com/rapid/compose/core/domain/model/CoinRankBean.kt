package com.rapid.compose.core.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class CoinRankBean(
    val coinCount: Int = 0,
    val level: Int = 0,
    val nickname: String? = "",
    val rank: String? = "",
    val userId: Int = 0,
    val username: String? = ""
)
