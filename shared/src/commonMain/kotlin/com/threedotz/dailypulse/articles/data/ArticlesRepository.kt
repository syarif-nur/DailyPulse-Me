package com.threedotz.dailypulse.articles.data

class ArticlesRepository(
    private val dataSource: ArticlesDataSource,
    private val service: ArticlesService
) {
    suspend fun getArticles(forceFetch: Boolean): List<ArticleRaw>{
        if(forceFetch){
            dataSource.cleanArticles()
            return fetchArticles()
        }
        val articlesDb = dataSource.getAllArticles()
        println("Got ${articlesDb.size} from database")

        if(articlesDb.isEmpty()){
            return fetchArticles()
        }

        return articlesDb
    }

    private suspend fun fetchArticles(): List<ArticleRaw>{
        val fetchedArticles = service.fetchArticles()
        dataSource.insertArticles(fetchedArticles)
        return fetchedArticles
    }
}