package com.rapid.compose.core.data.source.api

import com.rapid.compose.core.model.*
import retrofit2.http.*

/**
 * 内容相关接口（体系、项目、公众号等）。
 */
interface ContentApiService {

    @GET("/tree/json")
    suspend fun getKnowledgeTree(): ApiResponse<List<CategoryNodeBean>>

    @GET("/article/list/{page}/json")
    suspend fun getArticlesByCategory(
        @Path("page") page: Int,
        @Query("cid") categoryId: Int
    ): ApiResponse<ArticleListBean>

    @GET("/article/list/{page}/json")
    suspend fun getArticlesByAuthor(
        @Path("page") page: Int,
        @Query("author") author: String
    ): ApiResponse<ArticleListBean>

    @GET("/navi/json")
    suspend fun getNavigation(): ApiResponse<List<NavigationBean>>

    @GET("/project/tree/json")
    suspend fun getProjectTree(): ApiResponse<List<CategoryNodeBean>>

    @GET("/project/list/{page}/json")
    suspend fun getProjectArticles(
        @Path("page") page: Int,
        @Query("cid") categoryId: Int
    ): ApiResponse<ProjectPageBean>

    @GET("/wxarticle/chapters/json")
    suspend fun getWeChatChapters(): ApiResponse<List<WxChapterBean>>

    @GET("/wxarticle/list/{id}/{page}/json")
    suspend fun getWeChatArticles(
        @Path("id") chapterId: Int,
        @Path("page") page: Int
    ): ApiResponse<ArticleListBean>

    @GET("/wxarticle/list/{id}/{page}/json")
    suspend fun searchWeChatArticles(
        @Path("id") chapterId: Int,
        @Path("page") page: Int,
        @Query("k") keyword: String
    ): ApiResponse<ArticleListBean>

    @FormUrlEncoded
    @POST("/article/query/{page}/json")
    suspend fun searchArticles(
        @Path("page") page: Int,
        @Field("k") keyword: String
    ): ApiResponse<ArticleListBean>

    @GET("/wenda/list/{page}/json")
    suspend fun getQuestionAnswers(@Path("page") page: Int): ApiResponse<ArticleListBean>

    @GET("/wenda/comments/{questionId}/json")
    suspend fun getWendaComments(
        @Path("questionId") questionId: Int
    ): ApiResponse<PageBean<WendaCommentBean>>

    @GET("/user/{userId}/share_articles/{page}/json")
    suspend fun getUserSharedArticles(
        @Path("userId") userId: Int,
        @Path("page") page: Int
    ): ApiResponse<ArticleListBean>

    @GET("/popular/wenda/json")
    suspend fun getPopularWenda(): ApiResponse<List<ArticleListBean.Data>>

    @GET("/popular/column/json")
    suspend fun getPopularColumns(): ApiResponse<List<PopularColumnBean>>

    @GET("/popular/route/json")
    suspend fun getPopularRoutes(): ApiResponse<List<CategoryNodeBean>>

    @GET("/chapter/547/sublist/json")
    suspend fun getTutorialChapters(): ApiResponse<List<CategoryNodeBean>>

    @GET("/article/list/{page}/json")
    suspend fun getTutorialArticles(
        @Path("page") page: Int,
        @Query("cid") tutorialId: Int,
        @Query("order_type") orderType: Int = 1
    ): ApiResponse<ArticleListBean>

    @GET("/lg/coin/list/{page}/json")
    suspend fun getCoinRecords(@Path("page") page: Int): ApiResponse<PageBean<CoinRecordBean>>

    @GET("/coin/rank/{page}/json")
    suspend fun getCoinRank(@Path("page") page: Int): ApiResponse<PageBean<CoinRankBean>>
}
