package com.rapid.compose.core.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class RegisterBean(
    val isAdmin: Boolean = false,
    val chapterTops: List<String?> = emptyList(),
    val coinCount: Int = 0,
    val collectIds: List<String?> = emptyList(),
    val email: String? = "",
    val icon: String? = "",
    val id: Int = 0,
    val nickname: String? = "",
    val password: String? = "",
    val publicName: String? = "",
    val token: String? = "",
    val type: Int = 0,
    val username: String? = ""
)
