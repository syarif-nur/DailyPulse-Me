package com.threedotz.dailypulse.di

import app.cash.sqldelight.db.SqlDriver
import com.threedotz.dailypulse.db.DatabaseDriverFactory
import come.threedotz.dailypulse.db.DailyPulseDatabase
import org.koin.dsl.module

val databaseModule = module {
    single<SqlDriver> { DatabaseDriverFactory().createDriver() }

    single<DailyPulseDatabase> { DailyPulseDatabase(get()) }
}