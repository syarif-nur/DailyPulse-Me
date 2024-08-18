package com.threedotz.dailypulse.sources.data


class SourcesRepository(
    private  val dataSource: SourcesDataSource,
    private val service: SourcesService
) {
    suspend fun getSources(forceFetch: Boolean): List<SourcesRaw>{
        if(forceFetch){
            dataSource.cleanSources()
            return fetchSources()
        }
        val sourceDb = dataSource.getAllSources()
        println("Got ${sourceDb.size} from database")

        if(sourceDb.isEmpty()){
            return fetchSources()
        }
        return sourceDb
    }

    private suspend fun fetchSources(): List<SourcesRaw>{
        val fetchedSources = service.fetchSources()
        dataSource.insertSources(fetchedSources)
        return fetchedSources
    }
}