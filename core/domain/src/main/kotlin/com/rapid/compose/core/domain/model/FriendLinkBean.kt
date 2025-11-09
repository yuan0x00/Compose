package com.rapid.compose.core.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class FriendLinkBean(
    val category: String? = "",
    val icon: String? = "",
    val id: Int = 0,
    val link: String? = "",
    val name: String? = "",
    val order: Int = 0,
    val visible: Int = 0
)
