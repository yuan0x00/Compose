package com.rapid.compose.core.data.repository.content

import com.rapid.compose.core.data.repository.BaseRepository
import com.rapid.compose.core.data.source.content.ContentRemoteDataSource
import com.rapid.compose.core.model.*
import com.rapid.compose.core.network.Resource

class ContentRepository(
    private val remoteDataSource: ContentRemoteDataSource
) : BaseRepository() {

    suspend fun getKnowledgeTree(): Resource<List<CategoryNodeBean>> =
        request({ remoteDataSource.getKnowledgeTree() }) { it ?: emptyList() }

    suspend fun getArticlesByCategory(page: Int, categoryId: Int): Resource<ArticleListBean> =
        request({ remoteDataSource.getArticlesByCategory(page, categoryId) }) { it ?: ArticleListBean() }

    suspend fun getArticlesByAuthor(page: Int, author: String): Resource<ArticleListBean> =
        request({ remoteDataSource.getArticlesByAuthor(page, author) }) { it ?: ArticleListBean() }

    suspend fun getNavigation(): Resource<List<NavigationBean>> =
        request({ remoteDataSource.getNavigation() }) { it ?: emptyList() }

    suspend fun getProjectTree(): Resource<List<CategoryNodeBean>> =
        request({ remoteDataSource.getProjectTree() }) { it ?: emptyList() }

    suspend fun getProjectArticles(page: Int, categoryId: Int): Resource<ProjectPageBean> =
        request({ remoteDataSource.getProjectArticles(page, categoryId) }) { it ?: ProjectPageBean() }

    suspend fun getWeChatChapters(): Resource<List<WxChapterBean>> =
        request({ remoteDataSource.getWeChatChapters() }) { it ?: emptyList() }

    suspend fun getWeChatArticles(chapterId: Int, page: Int): Resource<ArticleListBean> =
        request({ remoteDataSource.getWeChatArticles(chapterId, page) }) { it ?: ArticleListBean() }

    suspend fun searchWeChatArticles(chapterId: Int, page: Int, keyword: String): Resource<ArticleListBean> =
        request({ remoteDataSource.searchWeChatArticles(chapterId, page, keyword) }) { it ?: ArticleListBean() }

    suspend fun searchArticles(page: Int, keyword: String): Resource<ArticleListBean> =
        request({ remoteDataSource.searchArticles(page, keyword) }) { it ?: ArticleListBean() }

    suspend fun getQuestionAnswers(page: Int): Resource<ArticleListBean> =
        request({ remoteDataSource.getQuestionAnswers(page) }) { it ?: ArticleListBean() }

    suspend fun getWendaComments(questionId: Int): Resource<PageBean<WendaCommentBean>> =
        request({ remoteDataSource.getWendaComments(questionId) }) { it ?: PageBean<WendaCommentBean>() }

    suspend fun getUserSharedArticles(userId: Int, page: Int): Resource<ArticleListBean> =
        request({ remoteDataSource.getUserSharedArticles(userId, page) }) { it ?: ArticleListBean() }

    suspend fun getPopularWenda(): Resource<List<ArticleListBean.Data>> =
        request({ remoteDataSource.getPopularWenda() }) { it ?: emptyList() }

    suspend fun getPopularColumns(): Resource<List<PopularColumnBean>> =
        request({ remoteDataSource.getPopularColumns() }) { it ?: emptyList() }

    suspend fun getPopularRoutes(): Resource<List<CategoryNodeBean>> =
        request({ remoteDataSource.getPopularRoutes() }) { it ?: emptyList() }

    suspend fun getTutorialChapters(): Resource<List<CategoryNodeBean>> =
        request({ remoteDataSource.getTutorialChapters() }) { it ?: emptyList() }

    suspend fun getTutorialArticles(page: Int, tutorialId: Int, orderType: Int = 1): Resource<ArticleListBean> =
        request({ remoteDataSource.getTutorialArticles(page, tutorialId, orderType) }) { it ?: ArticleListBean() }

    suspend fun getCoinRecords(page: Int): Resource<PageBean<CoinRecordBean>> =
        request({ remoteDataSource.getCoinRecords(page) }) { it ?: PageBean<CoinRecordBean>() }

    suspend fun getCoinRank(page: Int): Resource<PageBean<CoinRankBean>> =
        request({ remoteDataSource.getCoinRank(page) }) { it ?: PageBean<CoinRankBean>() }
}
