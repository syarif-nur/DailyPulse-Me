package com.threedotz.dailypulse.di

import com.threedotz.dailypulse.articles.di.articlesModule

val sharedKoinModules = listOf(
    articlesModule,
    networkModule
)