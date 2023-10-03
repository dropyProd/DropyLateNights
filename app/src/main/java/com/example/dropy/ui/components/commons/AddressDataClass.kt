package com.example.dropy.ui.components.commons

import com.example.dropy.network.models.directions.Distance
import io.andronicus.buupass.ui.home.textfield.autocomplete.AutoCompleteEntity
import java.util.*

data class AddressDataClass(
    val latitude: Double = 0.0,
    val longitude: Double = 0.0,
    val placeName: String = "Place name",
    val placeId: String = "Google place id",
    val region: String = "Region",
    val additionalInformation: String? = null,
    val locationTag: String? = "tag",
    val buildingName: String = "",
    val floorNumber: String = "",
    val customTag: String? = "",
    val distance: Int? = null,
) : AutoCompleteEntity {
    override fun filter(query: String): Boolean {
        return placeName.toLowerCase(Locale.getDefault())
            .startsWith(query.toLowerCase(Locale.getDefault()))
    }
}