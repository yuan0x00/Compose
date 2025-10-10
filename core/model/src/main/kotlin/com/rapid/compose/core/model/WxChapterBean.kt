package com.rapid.compose.core.model

import kotlinx.serialization.Serializable

@Serializable
data class WxChapterBean(
    val courseId: Int = 0,
    val id: Int = 0,
    val name: String? = "",
    val order: Int = 0,
    val parentChapterId: Int = 0,
    val visible: Int = 0,
    val children: List<WxChapterBean> = emptyList()
)
