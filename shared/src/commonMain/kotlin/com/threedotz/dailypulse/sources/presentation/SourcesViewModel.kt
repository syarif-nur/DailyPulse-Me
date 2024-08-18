package com.threedotz.dailypulse.sources.presentation

import com.threedotz.dailypulse.BaseViewModel
import com.threedotz.dailypulse.sources.application.SourcesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SourcesViewModel(private val useCase: SourcesUseCase): BaseViewModel() {
    private val _sourceState: MutableStateFlow<SourcesState> =
        MutableStateFlow(SourcesState(loading = true))

    val sourceState: StateFlow<SourcesState> get() = _sourceState

    init {
        getSources()
    }

    fun getSources(forceFetch: Boolean = false){
        scope.launch {
            _sourceState.emit(SourcesState(loading = true, sources = _sourceState.value.sources))

            val fetchedSources = useCase.getSources(forceFetch)

            _sourceState.emit(SourcesState(sources = fetchedSources))
        }
    }
}