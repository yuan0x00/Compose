package com.rapid.compose.core.data.source.api

import com.rapid.compose.core.model.*
import retrofit2.http.*

/**
 * 用户信息及收藏相关接口。
 */
interface UserApiService {

    @GET("/user/lg/userinfo/json")
    suspend fun getUserInfo(): ApiResponse<UserInfoBean>

    @GET("/lg/collect/list/{page}/json")
    suspend fun getCollectList(@Path("page") page: Int): ApiResponse<ArticleListBean>

    @FormUrlEncoded
    @POST("/lg/uncollect/{id}/json")
    suspend fun unCollectInMine(
        @Path("id") id: Int,
        @Field("originId") originId: Int
    ): ApiResponse<String>

    @POST("/lg/uncollect_originId/{id}/json")
    suspend fun unCollect(@Path("id") id: Int): ApiResponse<String>

    @GET("/lg/coin/userinfo/json")
    suspend fun signIn(): ApiResponse<CoinBean>

    @POST("/lg/collect/{id}/json")
    suspend fun collectArticle(@Path("id") id: Int): ApiResponse<String>

    @GET("/lg/collect/usertools/json")
    suspend fun getUserTools(): ApiResponse<List<UserToolBean>>

    @FormUrlEncoded
    @POST("/lg/collect/addtool/json")
    suspend fun addUserTool(
        @Field("name") name: String,
        @Field("link") link: String
    ): ApiResponse<UserToolBean>

    @FormUrlEncoded
    @POST("/lg/collect/updatetool/json")
    suspend fun updateUserTool(
        @Field("id") id: Int,
        @Field("name") name: String,
        @Field("link") link: String
    ): ApiResponse<UserToolBean>

    @FormUrlEncoded
    @POST("/lg/collect/deletetool/json")
    suspend fun deleteUserTool(@Field("id") id: Int): ApiResponse<String>

    @FormUrlEncoded
    @POST("/lg/collect/add/json")
    suspend fun collectOutside(
        @Field("title") title: String,
        @Field("author") author: String,
        @Field("link") link: String
    ): ApiResponse<ArticleListBean.Data>

    @FormUrlEncoded
    @POST("/lg/collect/user_article/update/{id}/json")
    suspend fun updateCollectedArticle(
        @Path("id") articleId: Int,
        @Field("title") title: String,
        @Field("link") link: String,
        @Field("author") author: String
    ): ApiResponse<ArticleListBean.Data>

    @GET("/user/lg/private_articles/{page}/json")
    suspend fun getPrivateShareArticles(
        @Path("page") page: Int,
        @Query("page_size") pageSize: Int? = null
    ): ApiResponse<UserShareBean>

    @POST("/lg/user_article/delete/{id}/json")
    suspend fun deleteShareArticle(@Path("id") articleId: Int): ApiResponse<String>

    @FormUrlEncoded
    @POST("/lg/user_article/add/json")
    suspend fun addShareArticle(
        @Field("title") title: String,
        @Field("link") link: String
    ): ApiResponse<ArticleListBean.Data>
}
