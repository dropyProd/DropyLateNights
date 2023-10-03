package com.example.dropy.ui.components.commons

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun EmptyBlock(modifier: Modifier = Modifier){
    Column(
        modifier = modifier
            .padding(vertical = 8.dp)
            .fillMaxWidth()
            .height(100.dp)
            .border(1.dp, Color.LightGray.copy(.4f), RoundedCornerShape(4.dp))
        ,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        SimpleText(
            text = "Oops! nothing here yet"
        )
    }
}

@Preview(showBackground = true)
@Composable
fun EmptyBlockPreview(){
    Column(Modifier.fillMaxSize()) {
        EmptyBlock()
    }
}