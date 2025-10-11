package com.rapid.compose

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.rapid.compose.core.data.repository.home.HomeRepository
import com.rapid.compose.core.model.ArticleListBean
import com.rapid.compose.core.network.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * 主页 ViewModel
 */
class MainViewModel(
    private val homeRepository: HomeRepository
) : ViewModel() {

    private val _articlesState = MutableStateFlow<Resource<List<ArticleListBean.Data>>>(Resource.Loading)
    val articlesState: StateFlow<Resource<List<ArticleListBean.Data>>> = _articlesState.asStateFlow()

    fun loadArticles() {
        viewModelScope.launch {
            _articlesState.value = Resource.Loading
            when (val result = homeRepository.getTopArticles()) {
                is Resource.Success -> {
                    val articles = result.result.map { it.toArticleItem() }
                    _articlesState.value = Resource.Success(articles)
                }
                is Resource.Error -> _articlesState.value = result
                Resource.Loading -> Unit
            }
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

    companion object {
        fun provideFactory(homeRepository: HomeRepository): ViewModelProvider.Factory {
            return object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    require(modelClass.isAssignableFrom(MainViewModel::class.java))
                    return MainViewModel(homeRepository) as T
                }
            }
        }
    }
}
