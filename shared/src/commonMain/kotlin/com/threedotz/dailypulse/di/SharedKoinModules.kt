package com.threedotz.dailypulse.di

import com.threedotz.dailypulse.articles.di.articlesModule
import com.threedotz.dailypulse.sources.di.sourcesModule

val sharedKoinModules = listOf(
    articlesModule,
    networkModule,
    sourcesModule
)