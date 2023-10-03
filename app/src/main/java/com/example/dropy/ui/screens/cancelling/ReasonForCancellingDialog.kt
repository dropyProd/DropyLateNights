package com.example.dropy.ui.screens.cancelling

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Switch
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.dropy.R
import com.example.dropy.ui.components.commons.LoadImage
import com.example.dropy.ui.components.commons.SimpleText
import com.example.dropy.ui.theme.LightBlue

@Composable
fun ReasonForCancellingDialog(
    reasonForCancellingDialogViewModel: ReasonForCancellingDialogViewModel? = null,
    show: Boolean = true,
    onDismisssDialog: ((Boolean) -> Unit)? = null,

) {

    val uiState by reasonForCancellingDialogViewModel?.reasonForCancellingDialogUiState!!.collectAsState()

   if (show){
       Dialog(onDismissRequest = {
           if (onDismisssDialog != null) {
               onDismisssDialog(false)
           }
       }) {

           Column(
               modifier = Modifier
                   .background(Color.White, RoundedCornerShape(12.dp))
                   .border(width = 1.dp, LightBlue, RoundedCornerShape(12.dp))
                   .fillMaxWidth()
                   .padding(horizontal = 12.dp, vertical = 12.dp),
               verticalArrangement = Arrangement.spacedBy(26.dp),
               horizontalAlignment = Alignment.CenterHorizontally
           ) {
               SimpleText(
                   text = "REASON FOR CANCELLING",
                   isUppercase = true,
                   fontWeight = FontWeight.ExtraBold,
                   textSize = 16,
                   font = Font(R.font.axiformaheavy)
               )

               Row(
                   verticalAlignment = Alignment.CenterVertically,
                   horizontalArrangement = Arrangement.SpaceBetween,
                   modifier = Modifier.fillMaxWidth()
               ) {
                   Row(
                       modifier = Modifier
                           .border(1.dp, Color.LightGray, RoundedCornerShape(13.dp))
                           .size(80.dp)
                   ) {
                       LoadImage(
                           imageUrl = uiState.productImageUrl,
                           contentScale = ContentScale.Crop,
                           modifier = Modifier
                               .clip(RoundedCornerShape(13.dp))
                               .size(80.dp)
                       )


                   }

                   Column(
                       modifier = Modifier
                           .padding(start = 14.dp)
                           .fillMaxWidth(),
                       verticalArrangement = Arrangement.spacedBy(10.dp)
                   ) {
                       switchText(
                           text = "OUT OF STOCK",
                           isChecked = uiState.outOfStockState,
                           onCheckChange = {
                               reasonForCancellingDialogViewModel?.outOfStockStateChange(it)
                           })
                       switchText(text = "TO RESTOCK LATER", isChecked = uiState.toReStockLaterState, onCheckChange = {
                           reasonForCancellingDialogViewModel?.toReStockLaterStateChange(it)
                       })
                   }

               }
           }
       }
   }
}

@Composable
fun switchText(text: String, isChecked: Boolean, onCheckChange: (Boolean) -> Unit) {
    Row(
        modifier = Modifier.wrapContentWidth(),
        horizontalArrangement = Arrangement.spacedBy(15.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Switch(checked = isChecked, onCheckedChange = onCheckChange)
        SimpleText(
            text = text,
            isUppercase = true,
            fontWeight = FontWeight.ExtraBold,
            textSize = 13,
            font = Font(R.font.axiformaheavy)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun demoo() {
    ReasonForCancellingDialog()
}