package com.rapid.compose

import android.app.Application
import android.os.Looper
import com.rapid.compose.core.network.NetworkClient
import com.rapid.compose.core.webview.core.WebViewManager
import com.rapid.compose.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

/**
 * 应用程序入口
 */
class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin{
            androidLogger()
            androidContext(this@MainApplication)
            modules(appModule)
        }

        // 初始化网络客户端
        NetworkClient.init(baseUrl = "https://www.wanandroid.com/")

        onIdleHandler()

    }

    private fun onIdleHandler() {
        Looper.myQueue().addIdleHandler {
            // 在主线程空闲时预热
            WebViewManager.getInstance().initPool(this)
            false // 只执行一次
        }
    }
}
