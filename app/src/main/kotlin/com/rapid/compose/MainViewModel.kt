package com.rapid.compose

import androidx.lifecycle.ViewModel
import com.rapid.compose.core.domain.model.ArticleListBean
import com.rapid.compose.core.network.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainViewModel : ViewModel() {

    private val _articlesState = MutableStateFlow<Resource<List<ArticleListBean.Data>>>(Resource.Loading)
    val articlesState: StateFlow<Resource<List<ArticleListBean.Data>>> = _articlesState.asStateFlow()

    fun loadArticles() {

    }
}
