package com.rapid.compose.core.model

import kotlinx.serialization.Serializable

@Serializable
data class UserArticleBean(
    val curPage: Int = 0,
    val datas: List<Data> = emptyList(),
    val offset: Int = 0,
    val isOver: Boolean = false,
    val pageCount: Int = 0,
    val size: Int = 0,
    val total: Int = 0
) {
    @Serializable
    data class Data(
        val isAdminAdd: Boolean = false,
        val apkLink: String? = "",
        val audit: Int = 0,
        val author: String? = "",
        val isCanEdit: Boolean = false,
        val chapterId: Int = 0,
        val chapterName: String? = "",
        val isCollect: Boolean = false,
        val courseId: Int = 0,
        val desc: String? = "",
        val descMd: String? = "",
        val envelopePic: String? = "",
        val isFresh: Boolean = false,
        val host: String? = "",
        val id: Int = 0,
        val link: String? = "",
        val niceDate: String? = "",
        val niceShareDate: String? = "",
        val origin: String? = "",
        val prefix: String? = "",
        val projectLink: String? = "",
        val publishTime: Long = 0L,
        val realSuperChapterId: Int = 0,
        val selfVisible: Int = 0,
        val shareDate: Long = 0L,
        val shareUser: String? = "",
        val superChapterId: Int = 0,
        val superChapterName: String? = "",
        val tags: List<String?> = emptyList(),
        val title: String? = "",
        val type: Int = 0,
        val userId: Int = 0,
        val visible: Int = 0,
        val zan: Int = 0
    )
}
