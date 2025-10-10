package com.rapid.compose.core.model

import kotlinx.serialization.Serializable

@Serializable
data class CoinRecordBean(
    val coinCount: Int = 0,
    val date: Long = 0L,
    val desc: String? = "",
    val id: Int = 0,
    val reason: String? = "",
    val type: Int = 0,
    val userId: Int = 0,
    val userName: String? = ""
)
