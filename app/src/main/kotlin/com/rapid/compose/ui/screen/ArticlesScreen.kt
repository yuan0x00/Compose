package com.rapid.compose.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.rapid.compose.MainViewModel
import com.rapid.compose.core.network.Resource
import com.rapid.compose.core.webview.ui.WebViewActivity
import org.koin.androidx.compose.koinViewModel

@Composable
fun ArticlesScreen(modifier: Modifier = Modifier) {
    val viewModel: MainViewModel = koinViewModel()
    val state by viewModel.articlesState.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.loadArticles()
    }

    Column(modifier = modifier) {
        when (state) {
            is Resource.Loading -> Unit
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
