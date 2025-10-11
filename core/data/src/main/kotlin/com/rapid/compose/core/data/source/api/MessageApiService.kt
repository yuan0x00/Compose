package com.rapid.compose.core.data.source.api

import com.rapid.compose.core.model.ApiResponse
import com.rapid.compose.core.model.MessageBean
import com.rapid.compose.core.model.PageBean
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * 消息通知相关接口。
 */
interface MessageApiService {

    @GET("/message/lg/count_unread/json")
    suspend fun getUnreadMessageCount(): ApiResponse<Int>

    @GET("/message/lg/readed_list/{page}/json")
    suspend fun getReadMessages(
        @Path("page") page: Int,
        @Query("page_size") pageSize: Int? = null
    ): ApiResponse<PageBean<MessageBean>>

    @GET("/message/lg/unread_list/{page}/json")
    suspend fun getUnreadMessages(
        @Path("page") page: Int,
        @Query("page_size") pageSize: Int? = null
    ): ApiResponse<PageBean<MessageBean>>
}
