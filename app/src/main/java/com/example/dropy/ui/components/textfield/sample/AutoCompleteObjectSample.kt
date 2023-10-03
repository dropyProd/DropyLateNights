/*
 * Copyright 2021 Paulo Pereira
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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



@ExperimentalAnimationApi
@Composable
fun AutoCompleteObjectSample(
    locations: List<AddressDataClass>,
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
            showdelivery = showdelivery
        )
    }
}

@Composable
fun CityAutoCompleteItem(item: AddressDataClass) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(text = item.placeName, style = MaterialTheme.typography.subtitle2, color = Color.Black)
        /*   Text(
               text = city
                   .alias, style = MaterialTheme.typography.subtitle2, color = Color.Black
           )*/
    }
}
