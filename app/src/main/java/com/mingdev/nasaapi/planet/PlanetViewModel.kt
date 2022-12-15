package com.mingdev.nasaapi.planet

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mingdev.nasaapi.planet.model.Planet
import com.mingdev.nasaapi.util.Lce
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlanetViewModel @Inject constructor(
    private val useCase: PlanetUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(State())
    val state: StateFlow<State> = _state.asStateFlow()


    init {
        viewModelScope.launch {
            _state.value = _state.value.copy(planetList = useCase.getRandomPlanets())
        }
    }

    fun performAction(action: Action) {
        viewModelScope.launch {
            when (action) {
                is Action.RefreshList -> {
                    _state.value = _state.value.copy(isRefreshing = true)
                    _state.value = _state.value.copy(planetList = useCase.getRandomPlanets())
                    _state.value = _state.value.copy(isRefreshing = false)

                }
            }
        }
    }

    data class State(
        val planetList: Lce<List<Planet>> = Lce.Loading,
        val isRefreshing: Boolean = false
    )

    sealed class Action {
        object RefreshList : Action()
    }

}

