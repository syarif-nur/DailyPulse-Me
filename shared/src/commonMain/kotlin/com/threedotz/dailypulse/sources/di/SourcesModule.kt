package com.threedotz.dailypulse.sources.di

import com.threedotz.dailypulse.articles.application.ArticlesUseCase
import com.threedotz.dailypulse.articles.data.ArticlesDataSource
import com.threedotz.dailypulse.articles.data.ArticlesRepository
import com.threedotz.dailypulse.articles.presentation.ArticlesViewModel
import com.threedotz.dailypulse.sources.application.SourcesUseCase
import com.threedotz.dailypulse.sources.data.SourcesDataSource
import com.threedotz.dailypulse.sources.data.SourcesRepository
import com.threedotz.dailypulse.sources.data.SourcesService
import com.threedotz.dailypulse.sources.presentation.SourcesViewModel
import org.koin.dsl.module

val sourcesModule = module {
    single<SourcesService>{ SourcesService(get()) }
    single<SourcesUseCase> { SourcesUseCase(get()) }
    single<SourcesViewModel> { SourcesViewModel(get()) }
    single<SourcesDataSource> { SourcesDataSource(get()) }
    single<SourcesRepository> { SourcesRepository(get(), get()) }

}