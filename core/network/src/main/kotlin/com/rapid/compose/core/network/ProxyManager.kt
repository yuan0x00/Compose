package com.rapid.compose.core.network

import android.util.Log
import java.io.IOException
import java.net.*
import java.util.concurrent.atomic.AtomicReference

data class ProxyConfig(
    val enabled: Boolean = false,
    val host: String = "192.168.1.1",
    val port: Int = 8888
)

object ProxyManager {
    private val currentConfig = AtomicReference(ProxyConfig())

    fun updateConfig(config: ProxyConfig) {
        Log.d(
            "ProxyManager",
            "Updating proxy config: enabled=${config.enabled}, host=${config.host}, port=${config.port}"
        )
        currentConfig.set(config)
        NetworkClient.recreateRetrofit() // 通知重建
    }

    fun getCurrentConfig(): ProxyConfig = currentConfig.get()

    // 提供 ProxySelector（每次调用都读取最新配置）
    val proxySelector = object : ProxySelector() {
        override fun select(uri: URI?): List<Proxy> {
            if (uri == null) return listOf(Proxy.NO_PROXY)

            val config = currentConfig.get()
            Log.d("ProxyManager", "ProxySelector.select() called for URI: $uri, enabled=${config.enabled}")
            return if (config.enabled) {
                Log.d("ProxyManager", "Returning proxy list: [${config.host}:${config.port}, DIRECT]")
                Log.d("ProxyManager", "Will try proxy first, fallback to direct connection if proxy fails")
                listOf(
                    Proxy(Proxy.Type.HTTP, InetSocketAddress(config.host, config.port)),
                    Proxy.NO_PROXY
                )
            } else {
                Log.d("ProxyManager", "Proxy disabled, using direct connection")
                listOf(Proxy.NO_PROXY)
            }
        }

        override fun connectFailed(uri: URI?, sa: SocketAddress?, ioe: IOException?) {
            Log.w("ProxyManager", "Proxy connection failed for URI: $uri, SocketAddress: $sa", ioe)
            Log.w("ProxyManager", "Falling back to direct connection")
        }
    }
}
