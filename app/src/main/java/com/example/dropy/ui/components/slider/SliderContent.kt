package com.example.dropy.ui.components.slider

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

//@Composable
//fun SliderContent(
//
//
//
//){
//    var sliderPositionState = remember { mutableStateOf(0f) }
//    Column(verticalArrangement = Arrangement.Center,
//        horizontalAlignment = Alignment.CenterHorizontally) {
//        Text(text = "33%")
//        Spacer(modifier = Modifier.height(15.dp))
//        Slider(
//            value = sliderPositionState.value,
//            valueRange = 100..5000,
//
//            onValueChange =  { newVal ->
//                sliderPositionState.value = newVal
//                Log.d("slider", "SliderContent: $newVal ")
//
//
//            },
//            modifier = Modifier.padding(start = 16.dp, end = 16.dp),
//            steps = 6,
//            onValueChangeFinished = {
////                Log.d("vail", "SliderContent: ")
//            }
//        )
//
//
//
//
//
//    }
//
//
//
//
//
//
//}
//
//fun Slider(
//    value: Int,
//    valueRange: IntRange,
//    onValueChange: (Float) -> Unit,
//    modifier: Modifier
//) {
//
//}
//
//
//@Preview(showBackground = true)
//@Composable
//fun ComposablePreview(){
//
//
//}