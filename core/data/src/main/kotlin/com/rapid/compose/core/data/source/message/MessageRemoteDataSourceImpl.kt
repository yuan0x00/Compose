package com.rapid.compose.core.data.source.message

import com.rapid.compose.core.data.source.api.MessageApiService
import com.rapid.compose.core.domain.model.ApiResponse
import com.rapid.compose.core.domain.model.MessageBean
import com.rapid.compose.core.domain.model.PageBean
import com.rapid.compose.core.network.NetworkClient

class MessageRemoteDataSourceImpl : MessageRemoteDataSource {

    private val apiService: MessageApiService by lazy { NetworkClient.create() }

    override suspend fun getUnreadMessageCount(): ApiResponse<Int> =
        apiService.getUnreadMessageCount()

    override suspend fun getReadMessages(
        page: Int,
        pageSize: Int?
    ): ApiResponse<PageBean<MessageBean>> = apiService.getReadMessages(page, pageSize)

    override suspend fun getUnreadMessages(
        page: Int,
        pageSize: Int?
    ): ApiResponse<PageBean<MessageBean>> = apiService.getUnreadMessages(page, pageSize)
}
