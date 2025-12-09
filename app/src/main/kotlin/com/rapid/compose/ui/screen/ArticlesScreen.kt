package com.rapid.compose.ui.screen

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.rapid.compose.MainViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun ArticlesScreen() {
    val viewModel: MainViewModel = koinViewModel()
    val state by viewModel.articlesState.collectAsState()
    val context = LocalContext.current

    LazyColumn() {
        items(100) { index ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(text = index.toString())
            }
        }
    }
}
