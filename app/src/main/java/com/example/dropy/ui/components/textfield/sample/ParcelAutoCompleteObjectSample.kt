package com.example.dropy.ui.components.textfield.sample

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.example.dropy.ui.components.commons.AddressDataClass
import io.andronicus.buupass.ui.home.textfield.autocomplete.AutoCompleteBox
import io.andronicus.buupass.ui.home.textfield.autocomplete.utils.AutoCompleteSearchBarTag
import io.andronicus.buupass.ui.home.textfield.searchbar.TextSearchBar

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun ParcelAutoCompleteObjectSample(locations: List<AddressDataClass>,
                                     modifier: Modifier = Modifier,
                                     value: String,
                                     onValueChange: (String) -> Unit,
                                     onClear: () -> Unit,
                                     label: String,
                                     showdelivery: Boolean = true
) {


    val visi = remember {
        mutableStateOf(
            false
        )
    }



    AutoCompleteBox(
    modifier = modifier,
    items = locations,
    itemContent = { location ->
        CityAutoCompleteItem(location)
    }
    ) {

        val view = LocalView.current

        onItemSelected {
            //value = city.name
            onValueChange(it.placeName)
            filter(value)
            view.clearFocus()
        }

        TextSearchBar(
            modifier = Modifier.testTag(AutoCompleteSearchBarTag),
            value = value,
            label = label,
            onDoneActionClick = {
                view.clearFocus()
            },
            onClearClick = {
                onClear()
                filter(value)
                view.clearFocus()
            },
            onFocusChanged = { focusState ->
                isSearching = focusState.isFocused
            },
            onValueChanged = { query ->
                onValueChange(query)
                //  value = query
                filter(value)
            },
            showdelivery = false
        )
    }
}
