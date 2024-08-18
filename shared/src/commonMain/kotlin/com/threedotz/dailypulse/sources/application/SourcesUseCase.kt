package com.threedotz.dailypulse.sources.application

import com.threedotz.dailypulse.sources.data.SourcesRaw
import com.threedotz.dailypulse.sources.data.SourcesRepository

class SourcesUseCase(private val repo: SourcesRepository) {
    suspend fun getSources(forceFetch: Boolean): List<Source>{
        val sourceRaw = repo.getSources(forceFetch)
        return mapToSources(sourceRaw)
    }

    private fun mapToSources(sourcesRaw: List<SourcesRaw>): List<Source> = sourcesRaw.map { raw ->
        Source(
            raw.name,
            raw.desc,
            joinString(raw.language,raw.country)
        )
    }

    private fun joinString(language: String, country: String):String{
        return "$language - $country"
    }
}