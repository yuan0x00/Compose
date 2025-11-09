package com.rapid.compose.core.data.source.content

import com.rapid.compose.core.data.source.api.ContentApiService
import com.rapid.compose.core.domain.model.*
import com.rapid.compose.core.network.NetworkClient

class ContentRemoteDataSourceImpl : ContentRemoteDataSource {

    private val apiService: ContentApiService by lazy { NetworkClient.create() }

    override suspend fun getKnowledgeTree(): ApiResponse<List<CategoryNodeBean>> =
        apiService.getKnowledgeTree()

    override suspend fun getArticlesByCategory(
        page: Int,
        categoryId: Int
    ): ApiResponse<ArticleListBean> = apiService.getArticlesByCategory(page, categoryId)

    override suspend fun getArticlesByAuthor(page: Int, author: String): ApiResponse<ArticleListBean> =
        apiService.getArticlesByAuthor(page, author)

    override suspend fun getNavigation(): ApiResponse<List<NavigationBean>> = apiService.getNavigation()

    override suspend fun getProjectTree(): ApiResponse<List<CategoryNodeBean>> =
        apiService.getProjectTree()

    override suspend fun getProjectArticles(page: Int, categoryId: Int): ApiResponse<ProjectPageBean> =
        apiService.getProjectArticles(page, categoryId)

    override suspend fun getWeChatChapters(): ApiResponse<List<WxChapterBean>> =
        apiService.getWeChatChapters()

    override suspend fun getWeChatArticles(chapterId: Int, page: Int): ApiResponse<ArticleListBean> =
        apiService.getWeChatArticles(chapterId, page)

    override suspend fun searchWeChatArticles(
        chapterId: Int,
        page: Int,
        keyword: String
    ): ApiResponse<ArticleListBean> = apiService.searchWeChatArticles(chapterId, page, keyword)

    override suspend fun searchArticles(page: Int, keyword: String): ApiResponse<ArticleListBean> =
        apiService.searchArticles(page, keyword)

    override suspend fun getQuestionAnswers(page: Int): ApiResponse<ArticleListBean> =
        apiService.getQuestionAnswers(page)

    override suspend fun getWendaComments(questionId: Int): ApiResponse<PageBean<WendaCommentBean>> =
        apiService.getWendaComments(questionId)

    override suspend fun getUserSharedArticles(userId: Int, page: Int): ApiResponse<ArticleListBean> =
        apiService.getUserSharedArticles(userId, page)

    override suspend fun getPopularWenda(): ApiResponse<List<ArticleListBean.Data>> =
        apiService.getPopularWenda()

    override suspend fun getPopularColumns(): ApiResponse<List<PopularColumnBean>> =
        apiService.getPopularColumns()

    override suspend fun getPopularRoutes(): ApiResponse<List<CategoryNodeBean>> =
        apiService.getPopularRoutes()

    override suspend fun getTutorialChapters(): ApiResponse<List<CategoryNodeBean>> =
        apiService.getTutorialChapters()

    override suspend fun getTutorialArticles(
        page: Int,
        tutorialId: Int,
        orderType: Int
    ): ApiResponse<ArticleListBean> = apiService.getTutorialArticles(page, tutorialId, orderType)

    override suspend fun getCoinRecords(page: Int): ApiResponse<PageBean<CoinRecordBean>> =
        apiService.getCoinRecords(page)

    override suspend fun getCoinRank(page: Int): ApiResponse<PageBean<CoinRankBean>> =
        apiService.getCoinRank(page)
}
