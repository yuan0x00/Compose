package com.rapid.compose.core.data.repository.home

import com.rapid.compose.core.data.repository.BaseRepository
import com.rapid.compose.core.data.source.home.HomeRemoteDataSource
import com.rapid.compose.core.model.*
import com.rapid.compose.core.network.Resource

class HomeRepository(
    private val remoteDataSource: HomeRemoteDataSource
) : BaseRepository() {

    suspend fun getBanner(): Resource<List<BannerItemBean>> =
        request({ remoteDataSource.getBanner() }) { it ?: emptyList() }

    suspend fun getArticleList(page: Int): Resource<ArticleListBean> =
        request({ remoteDataSource.getArticleList(page) }) { it ?: ArticleListBean() }

    suspend fun getLatestProjects(page: Int): Resource<ArticleListBean> =
        request({ remoteDataSource.getLatestProjects(page) }) { it ?: ArticleListBean() }

    suspend fun getPlazaArticles(page: Int, pageSize: Int? = null): Resource<ArticleListBean> =
        request({ remoteDataSource.getPlazaArticles(page, pageSize) }) { it ?: ArticleListBean() }

    suspend fun getTopArticles(): Resource<List<ArticleListBean.Data>> =
        request({ remoteDataSource.getTopArticles() }) { it ?: emptyList() }

    suspend fun getFriendLinks(): Resource<List<FriendLinkBean>> =
        request({ remoteDataSource.getFriendLinks() }) { it ?: emptyList() }

    suspend fun getHotKeys(): Resource<List<HotKeyBean>> =
        request({ remoteDataSource.getHotKeys() }) { it ?: emptyList() }

    suspend fun getHarmonyIndex(): Resource<HarmonyIndexBean> =
        request({ remoteDataSource.getHarmonyIndex() }) { it ?: HarmonyIndexBean() }

    suspend fun getToolList(): Resource<List<ToolItemBean>> =
        request({ remoteDataSource.getToolList() }) { it ?: emptyList() }
}
