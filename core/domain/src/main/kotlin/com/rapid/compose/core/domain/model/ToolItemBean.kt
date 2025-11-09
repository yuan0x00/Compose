package com.rapid.compose.core.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class ToolItemBean(
    val desc: String? = "",
    val icon: String? = "",
    val id: Int = 0,
    val isNew: Int = 0,
    val link: String? = "",
    val name: String? = "",
    val order: Int = 0,
    val showInTab: Int = 0,
    val tabName: String? = "",
    val visible: Int = 0
)
