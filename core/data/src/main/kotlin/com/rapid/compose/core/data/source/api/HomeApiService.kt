package com.rapid.compose.core.data.source.api

import com.rapid.compose.core.domain.model.*
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * 首页相关接口
 */
interface HomeApiService {

    @GET("/banner/json")
    suspend fun getBanner(): ApiResponse<List<BannerItemBean>>

    @GET("/article/list/{page}/json")
    suspend fun getArticleList(@Path("page") page: Int): ApiResponse<ArticleListBean>

    @GET("/article/listproject/{page}/json")
    suspend fun getLatestProjects(@Path("page") page: Int): ApiResponse<ArticleListBean>

    @GET("/user_article/list/{page}/json")
    suspend fun getPlazaArticles(
        @Path("page") page: Int,
        @Query("page_size") pageSize: Int? = null
    ): ApiResponse<ArticleListBean>

    @GET("/article/top/json")
    suspend fun getTopArticles(): ApiResponse<List<ArticleListBean.Data>>

    @GET("/friend/json")
    suspend fun getFriendLinks(): ApiResponse<List<FriendLinkBean>>

    @GET("/hotkey/json")
    suspend fun getHotKeys(): ApiResponse<List<HotKeyBean>>

    @GET("/harmony/index/json")
    suspend fun getHarmonyIndex(): ApiResponse<HarmonyIndexBean>

    @GET("/tools/list/json")
    suspend fun getToolList(): ApiResponse<List<ToolItemBean>>
}
