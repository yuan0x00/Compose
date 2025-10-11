package com.rapid.compose.di

import com.rapid.compose.core.data.repository.auth.AuthRepository
import com.rapid.compose.core.data.repository.content.ContentRepository
import com.rapid.compose.core.data.repository.home.HomeRepository
import com.rapid.compose.core.data.repository.message.MessageRepository
import com.rapid.compose.core.data.repository.user.UserRepository
import com.rapid.compose.core.data.source.auth.AuthRemoteDataSource
import com.rapid.compose.core.data.source.auth.AuthRemoteDataSourceImpl
import com.rapid.compose.core.data.source.content.ContentRemoteDataSource
import com.rapid.compose.core.data.source.content.ContentRemoteDataSourceImpl
import com.rapid.compose.core.data.source.home.HomeRemoteDataSource
import com.rapid.compose.core.data.source.home.HomeRemoteDataSourceImpl
import com.rapid.compose.core.data.source.message.MessageRemoteDataSource
import com.rapid.compose.core.data.source.message.MessageRemoteDataSourceImpl
import com.rapid.compose.core.data.source.user.UserRemoteDataSource
import com.rapid.compose.core.data.source.user.UserRemoteDataSourceImpl

/**
 * 应用级依赖容器，替代 Hilt 提供依赖管理。
 */
class AppContainer {

    private val homeRemoteDataSource: HomeRemoteDataSource by lazy { HomeRemoteDataSourceImpl() }
    private val contentRemoteDataSource: ContentRemoteDataSource by lazy { ContentRemoteDataSourceImpl() }
    private val authRemoteDataSource: AuthRemoteDataSource by lazy { AuthRemoteDataSourceImpl() }
    private val userRemoteDataSource: UserRemoteDataSource by lazy { UserRemoteDataSourceImpl() }
    private val messageRemoteDataSource: MessageRemoteDataSource by lazy { MessageRemoteDataSourceImpl() }

    val homeRepository: HomeRepository by lazy { HomeRepository(homeRemoteDataSource) }
    val contentRepository: ContentRepository by lazy { ContentRepository(contentRemoteDataSource) }
    val authRepository: AuthRepository by lazy { AuthRepository(authRemoteDataSource) }
    val userRepository: UserRepository by lazy { UserRepository(userRemoteDataSource) }
    val messageRepository: MessageRepository by lazy { MessageRepository(messageRemoteDataSource) }
}
