package com.rapid.compose.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.rapid.compose.viewmodel.ProxySettingsViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun ProxySettingsScreen() {
    val viewModel: ProxySettingsViewModel = koinViewModel()
    val config by viewModel.proxyConfig.observeAsState()

    var enabled by remember { mutableStateOf(config?.enabled ?: false) }
    var host by remember { mutableStateOf(config?.host ?: "") }
    var port by remember { mutableStateOf((config?.port ?: 0).toString()) }

    // 同步配置变化
    LaunchedEffect(config) {
        config?.let {
            enabled = it.enabled
            host = it.host
            port = it.port.toString()
        }
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = "代理设置",
                style = MaterialTheme.typography.titleMedium
            )

            HorizontalDivider()

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("启用代理")
                Switch(
                    checked = enabled,
                    onCheckedChange = {
                        enabled = it
                        viewModel.updateProxy(
                            enabled = enabled,
                            host = host,
                            port = port.toIntOrNull() ?: 8888
                        )
                    }
                )
            }

            OutlinedTextField(
                value = host,
                onValueChange = {
                    host = it
                    viewModel.updateProxy(enabled, host, port.toIntOrNull() ?: 8888)
                },
                label = { Text("代理主机") },
                modifier = Modifier.fillMaxWidth(),
                enabled = enabled
            )

            OutlinedTextField(
                value = port,
                onValueChange = {
                    port = it
                    viewModel.updateProxy(enabled, host, port.toIntOrNull() ?: 8888)
                },
                label = { Text("代理端口") },
                modifier = Modifier.fillMaxWidth(),
                enabled = enabled
            )

            if (enabled) {
                Text(
                    text = "代理地址: $host:$port",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}