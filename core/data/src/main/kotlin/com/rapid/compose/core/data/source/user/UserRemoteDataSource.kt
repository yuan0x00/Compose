package com.rapid.compose.core.data.source.user

import com.rapid.compose.core.domain.model.*

interface UserRemoteDataSource {
    suspend fun getUserInfo(): ApiResponse<UserInfoBean>
    suspend fun getCollectList(page: Int): ApiResponse<ArticleListBean>
    suspend fun unCollectInMine(id: Int, originId: Int): ApiResponse<String>
    suspend fun unCollect(id: Int): ApiResponse<String>
    suspend fun signIn(): ApiResponse<CoinBean>
    suspend fun collectArticle(id: Int): ApiResponse<String>
    suspend fun getUserTools(): ApiResponse<List<UserToolBean>>
    suspend fun addUserTool(name: String, link: String): ApiResponse<UserToolBean>
    suspend fun updateUserTool(id: Int, name: String, link: String): ApiResponse<UserToolBean>
    suspend fun deleteUserTool(id: Int): ApiResponse<String>
    suspend fun collectOutside(title: String, author: String, link: String): ApiResponse<ArticleListBean.Data>
    suspend fun updateCollectedArticle(articleId: Int, title: String, link: String, author: String): ApiResponse<ArticleListBean.Data>
    suspend fun getPrivateShareArticles(page: Int, pageSize: Int? = null): ApiResponse<UserShareBean>
    suspend fun deleteShareArticle(articleId: Int): ApiResponse<String>
    suspend fun addShareArticle(title: String, link: String): ApiResponse<ArticleListBean.Data>
}
