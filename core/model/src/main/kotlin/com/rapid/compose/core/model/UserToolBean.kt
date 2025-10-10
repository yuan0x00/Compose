package com.rapid.compose.core.model

import kotlinx.serialization.Serializable

@Serializable
data class UserToolBean(
    val desc: String? = "",
    val icon: String? = "",
    val id: Int = 0,
    val link: String? = "",
    val name: String? = "",
    val order: Int = 0,
    val type: Int = 0,
    val userId: Int = 0,
    val visible: Int = 0
)
