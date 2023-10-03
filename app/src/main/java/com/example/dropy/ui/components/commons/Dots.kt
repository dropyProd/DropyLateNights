package com.example.dropy.ui.components.commons

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun DotsRow(
    color:Color
){
    BoxWithConstraints(
        modifier = Modifier
            .padding(start = 8.dp, end = 8.dp)
            .fillMaxWidth(1f)
        ,
    ) {
        val width = this.maxWidth.value
        val dotsNumber = (width/8).toInt()
        Row(
            modifier = Modifier
                .fillMaxWidth()
            ,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            for (x in 1..dotsNumber){
                Dot(color = color)
            }
        }
    }
}

@Composable
fun DotsColumn(
    color:Color
){
    BoxWithConstraints(
        modifier = Modifier
            .padding(start = 8.dp, end = 8.dp)
            .fillMaxHeight()
        ,
    ) {
        val height = this.maxHeight.value
        val dotsNumber = (height/8).toInt()
        Column(
            modifier = Modifier
                .fillMaxHeight()
            ,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            for (x in 1..dotsNumber){
                Dot(color = color)
            }
        }
    }
}

@Composable
fun Dot(color: Color){
    Spacer(modifier = Modifier
        .padding(bottom = 2.dp)
        .size(2.dp)
        .clip(CircleShape)
        .background(color)
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewDotes(){
    Column(Modifier.fillMaxSize()) {
        DotsColumn(color = Color.Green)
        
    }
}