package com.mingdev.nasaapi.planet

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.mingdev.nasaapi.NavRoute.PlantDetail
import com.mingdev.nasaapi.planet.model.Planet
import com.mingdev.nasaapi.planet.model.toPlanet
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject


@HiltViewModel
class PlanetDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val planetJson: String? = savedStateHandle[PlantDetail.navArg]

    private val selectedPlanet: Planet? = planetJson?.toPlanet()

    private val _state = MutableStateFlow(State())
    val state: StateFlow<State> = _state.asStateFlow()

    init {
        selectedPlanet?.let {
            _state.value = _state.value.copy(selectedPlanet = it)

        }
    }

    data class State(
        val selectedPlanet: Planet? = null,
    )
}

