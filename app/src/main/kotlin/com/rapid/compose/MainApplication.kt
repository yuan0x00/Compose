package com.rapid.compose

import android.app.Application
import com.rapid.compose.core.network.NetworkClient
import com.rapid.compose.di.AppContainer

/**
 * 应用程序入口
 */
class MainApplication : Application() {

    lateinit var appContainer: AppContainer
        private set

    override fun onCreate() {
        super.onCreate()

        appContainer = AppContainer()

        // 初始化网络客户端
        NetworkClient.init(baseUrl = "https://www.wanandroid.com/")

    }
}
