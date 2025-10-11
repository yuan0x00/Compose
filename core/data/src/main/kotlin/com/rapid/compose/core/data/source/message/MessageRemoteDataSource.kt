package com.rapid.compose.core.data.source.message

import com.rapid.compose.core.model.ApiResponse
import com.rapid.compose.core.model.MessageBean
import com.rapid.compose.core.model.PageBean

interface MessageRemoteDataSource {
    suspend fun getUnreadMessageCount(): ApiResponse<Int>
    suspend fun getReadMessages(page: Int, pageSize: Int? = null): ApiResponse<PageBean<MessageBean>>
    suspend fun getUnreadMessages(page: Int, pageSize: Int? = null): ApiResponse<PageBean<MessageBean>>
}
