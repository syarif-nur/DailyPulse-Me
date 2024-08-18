package com.threedotz.dailypulse.sources.data

import come.threedotz.dailypulse.db.DailyPulseDatabase

class SourcesDataSource(private val database: DailyPulseDatabase) {
    fun getAllSources(): List<SourcesRaw> = database.dailyPulseDatabaseQueries.selectAllSources(::mapToSourceRaw).executeAsList()

    fun insertSources(sources: List<SourcesRaw>){
        database.dailyPulseDatabaseQueries.transaction {
            sources.forEach { sourceRaw ->
                insertSource(sourceRaw)
            }
        }
    }

    fun cleanSources() = database.dailyPulseDatabaseQueries.removeAllSources()

    private fun insertSource(sourceRaw: SourcesRaw){
        database.dailyPulseDatabaseQueries.insertSource(
            sourceRaw.name,
            sourceRaw.desc,
            sourceRaw.language,
            sourceRaw.country
        )
    }

    private fun mapToSourceRaw(
        name: String,
        desc: String,
        language: String,
        country: String
    ): SourcesRaw = SourcesRaw(
        name,
        desc,
        language,
        country
    )
}