package com.rapid.compose.core.data.source.home

import com.rapid.compose.core.domain.model.*

interface HomeRemoteDataSource {
    suspend fun getBanner(): ApiResponse<List<BannerItemBean>>
    suspend fun getArticleList(page: Int): ApiResponse<ArticleListBean>
    suspend fun getLatestProjects(page: Int): ApiResponse<ArticleListBean>
    suspend fun getPlazaArticles(page: Int, pageSize: Int? = null): ApiResponse<ArticleListBean>
    suspend fun getTopArticles(): ApiResponse<List<ArticleListBean.Data>>
    suspend fun getFriendLinks(): ApiResponse<List<FriendLinkBean>>
    suspend fun getHotKeys(): ApiResponse<List<HotKeyBean>>
    suspend fun getHarmonyIndex(): ApiResponse<HarmonyIndexBean>
    suspend fun getToolList(): ApiResponse<List<ToolItemBean>>
}
