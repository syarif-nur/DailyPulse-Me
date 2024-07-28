package com.threedotz.dailypulse.articles

import com.threedotz.dailypulse.BaseViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ArticlesViewModel: BaseViewModel() {

    private val _articleState: MutableStateFlow<ArticlesState> = MutableStateFlow(ArticlesState(loading = true))

    val articlesState: StateFlow<ArticlesState> get() = _articleState

    init {
        getArticles()
    }

    private  fun getArticles(){
        scope.launch {
            val fetchedArticle = fetchArticle()

            delay(1000)

            _articleState.emit(ArticlesState(articles = fetchedArticle))
        }
    }

    suspend fun fetchArticle(): List<Article> = mockArticle

    private val mockArticle = listOf(
        Article(
            "Stock Market",
            "Future",
            "2023-11-09",
            "https://en.wikipedia.org/wiki/Mega_Man_X#/media/File:Mega_Man_X_logo_(V2).png"
        )
    )

}