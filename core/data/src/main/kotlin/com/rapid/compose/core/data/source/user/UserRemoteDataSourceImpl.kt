package com.rapid.compose.core.data.source.user

import com.rapid.compose.core.data.source.api.UserApiService
import com.rapid.compose.core.domain.model.*
import com.rapid.compose.core.network.NetworkClient

class UserRemoteDataSourceImpl : UserRemoteDataSource {

    private val apiService: UserApiService by lazy { NetworkClient.create() }

    override suspend fun getUserInfo(): ApiResponse<UserInfoBean> = apiService.getUserInfo()

    override suspend fun getCollectList(page: Int): ApiResponse<ArticleListBean> =
        apiService.getCollectList(page)

    override suspend fun unCollectInMine(id: Int, originId: Int): ApiResponse<String> =
        apiService.unCollectInMine(id, originId)

    override suspend fun unCollect(id: Int): ApiResponse<String> = apiService.unCollect(id)

    override suspend fun signIn(): ApiResponse<CoinBean> = apiService.signIn()

    override suspend fun collectArticle(id: Int): ApiResponse<String> =
        apiService.collectArticle(id)

    override suspend fun getUserTools(): ApiResponse<List<UserToolBean>> =
        apiService.getUserTools()

    override suspend fun addUserTool(name: String, link: String): ApiResponse<UserToolBean> =
        apiService.addUserTool(name, link)

    override suspend fun updateUserTool(id: Int, name: String, link: String): ApiResponse<UserToolBean> =
        apiService.updateUserTool(id, name, link)

    override suspend fun deleteUserTool(id: Int): ApiResponse<String> = apiService.deleteUserTool(id)

    override suspend fun collectOutside(
        title: String,
        author: String,
        link: String
    ): ApiResponse<ArticleListBean.Data> = apiService.collectOutside(title, author, link)

    override suspend fun updateCollectedArticle(
        articleId: Int,
        title: String,
        link: String,
        author: String
    ): ApiResponse<ArticleListBean.Data> =
        apiService.updateCollectedArticle(articleId, title, link, author)

    override suspend fun getPrivateShareArticles(
        page: Int,
        pageSize: Int?
    ): ApiResponse<UserShareBean> = apiService.getPrivateShareArticles(page, pageSize)

    override suspend fun deleteShareArticle(articleId: Int): ApiResponse<String> =
        apiService.deleteShareArticle(articleId)

    override suspend fun addShareArticle(title: String, link: String): ApiResponse<ArticleListBean.Data> =
        apiService.addShareArticle(title, link)
}
