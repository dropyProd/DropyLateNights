package com.example.dropy.ui.components.reviews

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.dropy.ui.screens.review.RatingItem

@Composable
fun RiderRateItem(text: String) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(17.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = text,
            style = TextStyle(color = Color.Black, fontWeight = FontWeight.ExtraBold)
        )
        Row(
            modifier = Modifier.wrapContentWidth(),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            val isClickedOne = remember {
                mutableStateOf(false)
            }
            val isClickedTwo = remember {
                mutableStateOf(false)
            }
            val isClickedThree = remember {
                mutableStateOf(false)
            }
            val isClickedFour = remember {
                mutableStateOf(false)
            }
            val isClickedFive = remember {
                mutableStateOf(false)
            }


            RatingItem(i = 1, isCLiked = isClickedOne.value, clicked = {
                isClickedOne.value = !isClickedOne.value

                if (isClickedTwo.value == true || isClickedThree.value == true || isClickedFour.value == true || isClickedFive.value ==
                    true
                ) {
                    isClickedTwo.value = false
                    isClickedThree.value = false
                    isClickedFour.value = false
                    isClickedFive.value = false
                }

            })
            RatingItem(i = 2, isCLiked = isClickedTwo.value, clicked = {

                isClickedTwo.value = !isClickedTwo.value

                if (isClickedTwo.value == true) {
                    if (isClickedOne.value == false) isClickedOne.value = true

                }
                if (isClickedThree.value == true || isClickedFour.value == true || isClickedFive.value ==
                    true
                ) {
                    isClickedThree.value = false
                    isClickedFour.value = false
                    isClickedFive.value = false
                }
            })
            RatingItem(i = 3, isCLiked = isClickedThree.value, clicked = {

                isClickedThree.value = !isClickedThree.value

                if (isClickedThree.value == true) {
                    if (isClickedOne.value == false || isClickedTwo.value == false) {
                        isClickedOne.value = true
                        isClickedTwo.value = true

                    }


                }
                if (isClickedFour.value == true || isClickedFive.value ==
                    true
                ) {
                    isClickedFour.value = false
                    isClickedFive.value = false
                }
            })
            RatingItem(i = 4, isCLiked = isClickedFour.value, clicked = {

                isClickedFour.value = !isClickedFour.value
                if (isClickedFour.value) {
                    if (isClickedOne.value == false || isClickedTwo.value == false || isClickedThree.value == false) {
                        isClickedOne.value = true

                        isClickedTwo.value = true

                        isClickedThree.value = true


                    }


                }
                if (isClickedFive.value == true
                ) {
                    isClickedFive.value = false
                }
            })
            RatingItem(i = 5, isCLiked = isClickedFive.value, clicked = {

                isClickedFive.value = !isClickedFive.value
                if (isClickedFive.value) {
                    if (isClickedOne.value == false || isClickedTwo.value == false || isClickedThree.value == false || isClickedFour.value == false) {
                        isClickedOne.value = true

                        isClickedTwo.value = true

                        isClickedThree.value = true

                        isClickedFour.value = true


                    }
                }
            })


        }
    }
}