package com.threedotz.dailypulse.sources.data

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class SourcesService(private val httpClient: HttpClient){
    private val apiKey = "2024bd0744f44f62808393e09ef66ca8"

    suspend fun fetchSources(): List<SourcesRaw>{
        val response: SourcesResponse = httpClient.get("https://newsapi.org/v2/top-headlines/sources?apiKey=$apiKey").body()
        return response.sources
    }
}