package com.rapid.compose.core.data.repository.message

import com.rapid.compose.core.data.repository.BaseRepository
import com.rapid.compose.core.data.source.message.MessageRemoteDataSource
import com.rapid.compose.core.model.MessageBean
import com.rapid.compose.core.model.PageBean
import com.rapid.compose.core.network.Resource

class MessageRepository(
    private val remoteDataSource: MessageRemoteDataSource
) : BaseRepository() {

    suspend fun getUnreadMessageCount(): Resource<Int> =
        request({ remoteDataSource.getUnreadMessageCount() }) { it ?: 0 }

    suspend fun getReadMessages(page: Int, pageSize: Int? = null): Resource<PageBean<MessageBean>> =
        request({ remoteDataSource.getReadMessages(page, pageSize) }) { it ?: PageBean<MessageBean>() }

    suspend fun getUnreadMessages(page: Int, pageSize: Int? = null): Resource<PageBean<MessageBean>> =
        request({ remoteDataSource.getUnreadMessages(page, pageSize) }) { it ?: PageBean<MessageBean>() }
}
