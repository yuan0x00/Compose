package com.rapid.compose.core.model

import kotlinx.serialization.Serializable

@Serializable
data class BannerItemBean(
    val desc: String? = null,
    val id: Int = 0,
    val imagePath: String? = null,
    val isVisible: Int = 0,
    val order: Int = 0,
    val title: String? = null,
    val type: Int = 0,
    val url: String? = null
)
