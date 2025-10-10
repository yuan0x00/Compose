package com.rapid.compose.core.model

import kotlinx.serialization.Serializable

@Serializable
data class WendaCommentBean(
    val anonymous: Int = 0,
    val appendForContent: Int = 0,
    val articleId: Int = 0,
    val isCanEdit: Boolean = false,
    val content: String? = "",
    val contentMd: String? = "",
    val id: Int = 0,
    val niceDate: String? = "",
    val publishDate: Long = 0L,
    val replyCommentId: Int = 0,
    val replyComments: List<WendaCommentBean> = emptyList(),
    val rootCommentId: Int = 0,
    val status: Int = 0,
    val toUserId: Int = 0,
    val toUserName: String? = "",
    val userId: Int = 0,
    val userName: String? = "",
    val zan: Int = 0
)
