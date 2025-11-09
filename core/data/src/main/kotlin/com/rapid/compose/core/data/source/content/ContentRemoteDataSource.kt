package com.rapid.compose.core.data.source.content

import com.rapid.compose.core.domain.model.*

interface ContentRemoteDataSource {
    suspend fun getKnowledgeTree(): ApiResponse<List<CategoryNodeBean>>
    suspend fun getArticlesByCategory(page: Int, categoryId: Int): ApiResponse<ArticleListBean>
    suspend fun getArticlesByAuthor(page: Int, author: String): ApiResponse<ArticleListBean>
    suspend fun getNavigation(): ApiResponse<List<NavigationBean>>
    suspend fun getProjectTree(): ApiResponse<List<CategoryNodeBean>>
    suspend fun getProjectArticles(page: Int, categoryId: Int): ApiResponse<ProjectPageBean>
    suspend fun getWeChatChapters(): ApiResponse<List<WxChapterBean>>
    suspend fun getWeChatArticles(chapterId: Int, page: Int): ApiResponse<ArticleListBean>
    suspend fun searchWeChatArticles(chapterId: Int, page: Int, keyword: String): ApiResponse<ArticleListBean>
    suspend fun searchArticles(page: Int, keyword: String): ApiResponse<ArticleListBean>
    suspend fun getQuestionAnswers(page: Int): ApiResponse<ArticleListBean>
    suspend fun getWendaComments(questionId: Int): ApiResponse<PageBean<WendaCommentBean>>
    suspend fun getUserSharedArticles(userId: Int, page: Int): ApiResponse<ArticleListBean>
    suspend fun getPopularWenda(): ApiResponse<List<ArticleListBean.Data>>
    suspend fun getPopularColumns(): ApiResponse<List<PopularColumnBean>>
    suspend fun getPopularRoutes(): ApiResponse<List<CategoryNodeBean>>
    suspend fun getTutorialChapters(): ApiResponse<List<CategoryNodeBean>>
    suspend fun getTutorialArticles(page: Int, tutorialId: Int, orderType: Int = 1): ApiResponse<ArticleListBean>
    suspend fun getCoinRecords(page: Int): ApiResponse<PageBean<CoinRecordBean>>
    suspend fun getCoinRank(page: Int): ApiResponse<PageBean<CoinRankBean>>
}
