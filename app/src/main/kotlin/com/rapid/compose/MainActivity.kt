package com.rapid.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.rapid.compose.core.network.Resource
import com.rapid.compose.ui.screen.ProxySettingsScreen
import com.rapid.compose.ui.screen.ProxySettingsViewModel
import com.rapid.compose.ui.theme.ComposeTheme

/**
 * 主页 Activity
 */
class MainActivity : ComponentActivity() {

    private val appContainer by lazy { (application as MyApplication).appContainer }

    private val viewModel: MainViewModel by viewModels {
        MainViewModel.provideFactory(appContainer.homeRepository)
    }
    private val proxySettingsViewModel: ProxySettingsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            ComposeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(modifier = Modifier.padding(innerPadding)) {
                        ArticlesScreen(viewModel)
                        ProxySettingsScreen(proxySettingsViewModel)
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Composable
fun ArticlesScreen(viewModel: MainViewModel) {
    val state by viewModel.articlesState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadArticles()
    }

    Column {
        when (state) {
            is Resource.Loading -> CircularProgressIndicator()
            is Resource.Success -> {
                val articles = (state as Resource.Success).result
                Text(
                    text = articles.take(3).joinToString("\n\n") {
                        "标题: ${it.title}\n作者: ${it.author ?: it.shareUser}"
                    },
                    modifier = Modifier.clickable(onClick = { viewModel.loadArticles() })
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

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComposeTheme {
        Greeting("Android")
    }
}
