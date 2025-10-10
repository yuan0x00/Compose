package com.rapid.compose.core.model

import kotlinx.serialization.Serializable

@Serializable
data class CategoryNodeBean(
    val courseId: Int = 0,
    val id: Int = 0,
    val name: String? = "",
    val order: Int = 0,
    val parentChapterId: Int = 0,
    val isUserControlSetTop: Boolean = false,
    val visible: Int = 0,
    val type: Int = 0,
    val author: String? = "",
    val cover: String? = "",
    val desc: String? = "",
    val lisense: String? = "",
    val lisenseLink: String? = "",
    val link: String? = "",
    val articleList: List<ArticleListBean.Data> = emptyList(),
    val children: List<CategoryNodeBean> = emptyList()
)
