package com.threedotz.dailypulse.android.di

import app.cash.sqldelight.db.SqlDriver
import com.threedotz.dailypulse.db.DatabaseDriverFactory
import come.threedotz.dailypulse.db.DailyPulseDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module{
    single<SqlDriver>{DatabaseDriverFactory(androidContext()).createDriverFunction()}

    single<DailyPulseDatabase>{DailyPulseDatabase(get())}
}