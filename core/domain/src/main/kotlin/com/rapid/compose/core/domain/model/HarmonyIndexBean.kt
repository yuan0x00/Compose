package com.rapid.compose.core.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HarmonyIndexBean(
    val links: CategoryNodeBean = CategoryNodeBean(),
    @SerialName("open_sources") val openSources: CategoryNodeBean = CategoryNodeBean(),
    val tools: CategoryNodeBean = CategoryNodeBean()
)
