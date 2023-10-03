package com.example.dropy.ui.components.commons.maps.autocopletepredictions

import com.google.android.libraries.places.api.model.AutocompletePrediction

data class AutoCompleteUiState(
    val queryString: String = "",
    val autocompletePredictions: List<AutocompletePrediction> = emptyList()
)
