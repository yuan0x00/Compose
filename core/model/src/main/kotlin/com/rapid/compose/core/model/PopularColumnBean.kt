package com.rapid.compose.core.model

import kotlinx.serialization.Serializable

@Serializable
data class PopularColumnBean(
    val chapterId: Int = 0,
    val chapterName: String? = "",
    val columnId: Int = 0,
    val id: Int = 0,
    val name: String? = "",
    val subChapterId: Int = 0,
    val subChapterName: String? = "",
    val url: String? = "",
    val userId: Int = 0
)
