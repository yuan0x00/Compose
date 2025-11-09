package com.rapid.compose

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rapid.compose.core.domain.model.ArticleListBean
import com.rapid.compose.core.network.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * 主页 ViewModel
 */
class MainViewModel : ViewModel() {

    private val _articlesState = MutableStateFlow<Resource<List<ArticleListBean.Data>>>(Resource.Loading)
    val articlesState: StateFlow<Resource<List<ArticleListBean.Data>>> = _articlesState.asStateFlow()

    fun loadArticles() {
        viewModelScope.launch {

        }
    }

    private fun ArticleListBean.Data.toArticleItem(): ArticleListBean.Data {
        return ArticleListBean.Data(
            id = id,
            title = title.orEmpty(),
            author = author,
            shareUser = shareUser,
            link = link
        )
    }
}
