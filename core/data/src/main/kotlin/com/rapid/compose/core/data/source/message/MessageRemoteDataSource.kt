package com.rapid.compose.core.data.source.message

import com.rapid.compose.core.domain.model.ApiResponse
import com.rapid.compose.core.domain.model.MessageBean
import com.rapid.compose.core.domain.model.PageBean

interface MessageRemoteDataSource {
    suspend fun getUnreadMessageCount(): ApiResponse<Int>
    suspend fun getReadMessages(page: Int, pageSize: Int? = null): ApiResponse<PageBean<MessageBean>>
    suspend fun getUnreadMessages(page: Int, pageSize: Int? = null): ApiResponse<PageBean<MessageBean>>
}
