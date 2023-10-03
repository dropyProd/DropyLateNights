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
package io.andronicus.buupass.ui.home.textfield.searchbar

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dropy.ui.components.commons.SimpleText

@Composable
fun TextSearchBar(
    modifier: Modifier = Modifier,
    value: String,
    label: String,
    onDoneActionClick: () -> Unit = {},
    onClearClick: () -> Unit = {},
    onFocusChanged: (FocusState) -> Unit = {},
    onValueChanged: (String) -> Unit,
    showdelivery: Boolean = true
) {

   // val focusRequester = FocusRequester()
    val focusRequester = remember { FocusRequester() }


    LaunchedEffect(key1 = true ) {
        focusRequester.requestFocus()
    }


    var text by remember { mutableStateOf(TextFieldValue(value, selection = TextRange(value.length))) }

    if (showdelivery) {
        OutlinedTextField(
            value = text,
            onValueChange = { query ->
                text = query
                onValueChanged(text.text)
            },
            // readOnly = true,
            placeholder = {
                SimpleText(
                    text = "delivery location",
                    isUppercase = true,
                    textSize = 10
                )
            },
            modifier = modifier
                .padding(top = 8.dp, end = 8.dp)
                .fillMaxWidth(/*.9f*/)
                .focusRequester(focusRequester = focusRequester)
                .onFocusChanged { onFocusChanged(it) }
                .height(48.dp),
            leadingIcon = {
                Icon(imageVector = Icons.Filled.LocationOn, contentDescription = "")
            },
            trailingIcon = {
                if (!value.equals("")) {
                    IconButton(onClick = { onClearClick() }) {
                        Icon(imageVector = Icons.Filled.Clear, contentDescription = "Clear")
                    }
                }

            },
            keyboardActions = KeyboardActions(onDone = { onDoneActionClick() }),

            )
    } else {
        TextField(
            value = text,
            onValueChange = { query ->
                text = query
                onValueChanged(text.text)
            },
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(focusRequester = focusRequester)
                .onFocusChanged { onFocusChanged(it) },
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color(0xFF584AFF),
                unfocusedIndicatorColor = Color.Transparent,
                cursorColor = Color(0xFF584AFF),
                focusedLabelColor = Color.LightGray,
                unfocusedLabelColor = Color.LightGray.copy(.7f),
                backgroundColor = Color.Transparent
            ),
            placeholder = {
                Text(text = label)
            },
            singleLine = true,
            trailingIcon = {
                if (!value.equals("")) {
                    IconButton(onClick = { onClearClick() }) {
                        Icon(imageVector = Icons.Filled.Clear, contentDescription = "Clear")
                    }
                }

            }
        )
    }

}
