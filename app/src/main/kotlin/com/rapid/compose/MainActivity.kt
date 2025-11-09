package com.rapid.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.rapid.compose.core.network.Resource
import com.rapid.compose.core.webview.ui.WebViewActivity
import com.rapid.compose.ui.screen.ProxySettingsScreen
import com.rapid.compose.ui.screen.ProxySettingsViewModel
import com.rapid.compose.ui.theme.ComposeTheme

/**
 * 主页 Activity
 */
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()
    private val proxySettingsViewModel: ProxySettingsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            ComposeTheme {
                HomeScreen(
                    articlesViewModel = viewModel,
                    proxySettingsViewModel = proxySettingsViewModel
                )
            }
        }
    }
}

@Composable
fun ArticlesScreen(viewModel: MainViewModel, modifier: Modifier = Modifier) {
    val state by viewModel.articlesState.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.loadArticles()
    }

    Column(modifier = modifier) {
        when (state) {
            is Resource.Loading -> CircularProgressIndicator()
            is Resource.Success -> {
                val articles = (state as Resource.Success).result
                Text(
                    text = articles.take(3).joinToString("\n\n") {
                        "标题: ${it.title}\n作者: ${it.author ?: it.shareUser}"
                    },
                    modifier = Modifier.clickable(onClick = {
                        WebViewActivity.start(context, "www.wanandroid.com")
                    })
                )
            }

            is Resource.Error -> {
                Text(
                    text = "错误: ${(state as Resource.Error).error.message}",
                    modifier = Modifier.clickable(onClick = { viewModel.loadArticles() })
                )
            }
        }
    }
}

@Composable
fun HomeScreen(
    articlesViewModel: MainViewModel,
    proxySettingsViewModel: ProxySettingsViewModel
) {
    var selectedTab by rememberSaveable { mutableStateOf(HomeTab.Home) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            HomeBottomBar(
                selectedTab = selectedTab,
                onTabSelected = { selectedTab = it }
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when (selectedTab) {
                HomeTab.Home -> ArticlesScreen(
                    viewModel = articlesViewModel,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                )

                HomeTab.Settings -> ProxySettingsScreen(proxySettingsViewModel)
                HomeTab.Profile -> ProfileScreen()
            }
        }
    }
}

@Composable
private fun HomeBottomBar(
    selectedTab: HomeTab,
    onTabSelected: (HomeTab) -> Unit
) {
    NavigationBar {
        HomeTab.entries.forEach { tab ->
            NavigationBarItem(
                selected = tab == selectedTab,
                onClick = { onTabSelected(tab) },
                icon = {
                    Icon(
                        imageVector = tab.icon,
                        contentDescription = tab.label
                    )
                },
                label = { Text(text = tab.label) }
            )
        }
    }
}

private enum class HomeTab(val label: String, val icon: ImageVector) {
    Home(label = "首页", icon = Icons.Rounded.Home),
    Settings(label = "设置", icon = Icons.Rounded.Settings),
    Profile(label = "我的", icon = Icons.Rounded.Person)
}

@Composable
private fun ProfileScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "个人中心内容敬请期待")
    }
}
