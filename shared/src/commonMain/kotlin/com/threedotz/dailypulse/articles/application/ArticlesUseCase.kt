package com.threedotz.dailypulse.articles.application

import com.threedotz.dailypulse.articles.data.ArticleRaw
import com.threedotz.dailypulse.articles.data.ArticlesRepository
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.daysUntil
import kotlinx.datetime.toLocalDateTime
import kotlinx.datetime.todayIn
import kotlin.math.abs

class ArticlesUseCase(private val repo: ArticlesRepository) {
    suspend fun getArticles(forceFetch: Boolean): List<Article>{
        val articlesRaw = repo.getArticles(forceFetch)
        return mapArticles(articlesRaw)
    }

    private fun mapArticles(articlesRaw: List<ArticleRaw>): List<Article> = articlesRaw.map { raw->
        Article(
            raw.title,
            raw.desc ?: "Click to know more",
            getDaysAgoString(raw.date),
            raw.imageUrl ?: "https://i0.wp.com/thinkfirstcommunication.com/wp-content/uploads/2022/05/placeholder-1-1.png?w=1200&ssl=1",
        )
    }

    private fun getDaysAgoString(date: String): String{
        val today = Clock.System.todayIn(TimeZone.currentSystemDefault())
        val days = today.daysUntil(
            Instant.parse(date).toLocalDateTime(TimeZone.currentSystemDefault()).date
        )
        val result = when{
            abs(days) > 1 -> "${abs(days)} days ago"
            abs(days) == 1 -> "yesterday"
            else -> "Today"
        }
        return result
    }
}