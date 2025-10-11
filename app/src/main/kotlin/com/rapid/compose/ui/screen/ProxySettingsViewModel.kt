package com.rapid.compose.ui.screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rapid.compose.core.network.ProxyConfig
import com.rapid.compose.core.network.ProxyManager

class ProxySettingsViewModel : ViewModel() {
    private val _proxyConfig = MutableLiveData(ProxyManager.getCurrentConfig())
    val proxyConfig: LiveData<ProxyConfig> = _proxyConfig

    fun updateProxy(enabled: Boolean, host: String, port: Int) {
        val newConfig = ProxyConfig(enabled, host, port)
        ProxyManager.updateConfig(newConfig)
        _proxyConfig.value = newConfig
    }
}