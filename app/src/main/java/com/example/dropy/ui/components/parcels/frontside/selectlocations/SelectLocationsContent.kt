package com.example.dropy.ui.components.parcels.frontside.selectlocations
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.MyLocation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dropy.ui.components.commons.SimpleText
import com.example.dropy.ui.components.commons.StyledText
import com.example.dropy.ui.components.commons.TotallyRoundedButton
import com.example.dropy.ui.components.commons.maps.MapComponent
import com.example.dropy.ui.theme.DropyYellow
import com.example.dropy.ui.theme.LightBlue

@Composable
fun SelectLocationsContent(){
    Box(
        modifier = Modifier
            .fillMaxSize()
        ,
    ){
        Box(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .fillMaxHeight(0.75f)
        ){
            MapComponent()
        }
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
        ){
            SelectLocationsForm()
        }
    }
}







@Preview(showBackground = true)
@Composable
fun SelectLocationsContentPreview() {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
    ) {
        SelectLocationsContent()
    }
}