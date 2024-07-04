package com.odisby.robolectricpoc.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

data class FirstViewState(
    val shouldDisplaySecretTextView: Boolean = false,
)

class FirstViewModel : ViewModel() {

    private val _state = MutableStateFlow(FirstViewState())

    val state: StateFlow<FirstViewState>
        get() = _state

    fun logicalOne() {
        _state.value = state.value.copy(shouldDisplaySecretTextView = true)
    }

    fun logicalTwo() {
        _state.value = state.value.copy(shouldDisplaySecretTextView = false)
    }
}