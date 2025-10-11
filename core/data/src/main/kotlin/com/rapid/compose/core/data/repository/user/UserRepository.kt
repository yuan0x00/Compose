package com.rapid.compose.core.data.repository.user

import com.rapid.compose.core.data.repository.BaseRepository
import com.rapid.compose.core.data.source.user.UserRemoteDataSource
import com.rapid.compose.core.model.*
import com.rapid.compose.core.network.Resource

class UserRepository(
    private val remoteDataSource: UserRemoteDataSource
) : BaseRepository() {

    suspend fun getUserInfo(): Resource<UserInfoBean> =
        request({ remoteDataSource.getUserInfo() }) { it ?: UserInfoBean() }

    suspend fun getCollectList(page: Int): Resource<ArticleListBean> =
        request({ remoteDataSource.getCollectList(page) }) { it ?: ArticleListBean() }

    suspend fun unCollectInMine(id: Int, originId: Int): Resource<String> =
        request({ remoteDataSource.unCollectInMine(id, originId) }) { it ?: "" }

    suspend fun unCollect(id: Int): Resource<String> =
        request({ remoteDataSource.unCollect(id) }) { it ?: "" }

    suspend fun signIn(): Resource<CoinBean> =
        request({ remoteDataSource.signIn() }) { it ?: CoinBean() }

    suspend fun collectArticle(id: Int): Resource<String> =
        request({ remoteDataSource.collectArticle(id) }) { it ?: "" }

    suspend fun getUserTools(): Resource<List<UserToolBean>> =
        request({ remoteDataSource.getUserTools() }) { it ?: emptyList() }

    suspend fun addUserTool(name: String, link: String): Resource<UserToolBean> =
        request({ remoteDataSource.addUserTool(name, link) }) { it ?: UserToolBean() }

    suspend fun updateUserTool(id: Int, name: String, link: String): Resource<UserToolBean> =
        request({ remoteDataSource.updateUserTool(id, name, link) }) { it ?: UserToolBean() }

    suspend fun deleteUserTool(id: Int): Resource<String> =
        request({ remoteDataSource.deleteUserTool(id) }) { it ?: "" }

    suspend fun collectOutside(title: String, author: String, link: String): Resource<ArticleListBean.Data> =
        request({ remoteDataSource.collectOutside(title, author, link) }) { it ?: ArticleListBean.Data() }

    suspend fun updateCollectedArticle(
        articleId: Int,
        title: String,
        link: String,
        author: String
    ): Resource<ArticleListBean.Data> =
        request({ remoteDataSource.updateCollectedArticle(articleId, title, link, author) }) { it ?: ArticleListBean.Data() }

    suspend fun getPrivateShareArticles(page: Int, pageSize: Int? = null): Resource<UserShareBean> =
        request({ remoteDataSource.getPrivateShareArticles(page, pageSize) }) { it ?: UserShareBean() }

    suspend fun deleteShareArticle(articleId: Int): Resource<String> =
        request({ remoteDataSource.deleteShareArticle(articleId) }) { it ?: "" }

    suspend fun addShareArticle(title: String, link: String): Resource<ArticleListBean.Data> =
        request({ remoteDataSource.addShareArticle(title, link) }) { it ?: ArticleListBean.Data() }
}
