package com.threedotz.dailypulse.di

import com.threedotz.dailypulse.articles.ArticlesViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.KoinContext
import org.koin.core.context.startKoin

fun initKoin(){
    val modules = sharedKoinModules + databaseModule

    startKoin {
        modules(modules)
    }


}

class ArticlesInjector: KoinComponent{
    val articlesViewModel: ArticlesViewModel by inject()
}