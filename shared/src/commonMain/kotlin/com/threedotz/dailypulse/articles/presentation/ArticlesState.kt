package com.threedotz.dailypulse.articles.presentation

import com.threedotz.dailypulse.articles.application.Article

data class ArticlesState (
    val articles: List<Article> = listOf(),
    val loading: Boolean = false,
    val error: String? = null
)
