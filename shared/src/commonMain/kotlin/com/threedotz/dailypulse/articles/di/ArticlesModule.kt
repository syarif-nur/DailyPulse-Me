package com.threedotz.dailypulse.articles.di

import com.threedotz.dailypulse.articles.ArticlesService
import com.threedotz.dailypulse.articles.ArticlesUseCase
import com.threedotz.dailypulse.articles.ArticlesViewModel
import org.koin.dsl.module

val articlesModule = module {

    single<ArticlesService> { ArticlesService(get()) }
    single<ArticlesUseCase> { ArticlesUseCase(get()) }
    single<ArticlesViewModel>{ArticlesViewModel(get())}

}