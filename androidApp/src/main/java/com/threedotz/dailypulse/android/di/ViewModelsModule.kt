package com.threedotz.dailypulse.android.di

import com.threedotz.dailypulse.articles.presentation.ArticlesViewModel
import com.threedotz.dailypulse.sources.presentation.SourcesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelsModule = module {
    viewModel { ArticlesViewModel(get()) }
    viewModel { SourcesViewModel(get()) }
}