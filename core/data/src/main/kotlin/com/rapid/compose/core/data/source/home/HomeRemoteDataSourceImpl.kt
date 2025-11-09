package com.rapid.compose.core.data.source.home

import com.rapid.compose.core.data.source.api.HomeApiService
import com.rapid.compose.core.domain.model.*
import com.rapid.compose.core.network.NetworkClient

class HomeRemoteDataSourceImpl : HomeRemoteDataSource {

    private val apiService: HomeApiService by lazy { NetworkClient.create() }

    override suspend fun getBanner(): ApiResponse<List<BannerItemBean>> = apiService.getBanner()

    override suspend fun getArticleList(page: Int): ApiResponse<ArticleListBean> =
        apiService.getArticleList(page)

    override suspend fun getLatestProjects(page: Int): ApiResponse<ArticleListBean> =
        apiService.getLatestProjects(page)

    override suspend fun getPlazaArticles(page: Int, pageSize: Int?): ApiResponse<ArticleListBean> =
        apiService.getPlazaArticles(page, pageSize)

    override suspend fun getTopArticles(): ApiResponse<List<ArticleListBean.Data>> =
        apiService.getTopArticles()

    override suspend fun getFriendLinks(): ApiResponse<List<FriendLinkBean>> =
        apiService.getFriendLinks()

    override suspend fun getHotKeys(): ApiResponse<List<HotKeyBean>> = apiService.getHotKeys()

    override suspend fun getHarmonyIndex(): ApiResponse<HarmonyIndexBean> =
        apiService.getHarmonyIndex()

    override suspend fun getToolList(): ApiResponse<List<ToolItemBean>> = apiService.getToolList()
}
