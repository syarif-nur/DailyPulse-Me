package com.threedotz.dailypulse.sources.presentation

import com.threedotz.dailypulse.sources.application.Source

data class SourcesState (
    val sources: List<Source> = listOf(),
    val loading: Boolean = false,
    val error: String? = null,
)