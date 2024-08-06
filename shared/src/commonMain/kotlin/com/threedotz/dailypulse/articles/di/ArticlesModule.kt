package com.threedotz.dailypulse.articles.di

import com.threedotz.dailypulse.articles.data.ArticlesDataSource
import com.threedotz.dailypulse.articles.data.ArticlesRepository
import com.threedotz.dailypulse.articles.data.ArticlesService
import com.threedotz.dailypulse.articles.application.ArticlesUseCase
import com.threedotz.dailypulse.articles.presentation.ArticlesViewModel
import org.koin.dsl.module

val articlesModule = module {

    single<ArticlesService> { ArticlesService(get()) }
    single<ArticlesUseCase> { ArticlesUseCase(get()) }
    single<ArticlesViewModel> { ArticlesViewModel(get()) }
    single<ArticlesDataSource> { ArticlesDataSource(get()) }
    single<ArticlesRepository> { ArticlesRepository(get(), get()) }

}