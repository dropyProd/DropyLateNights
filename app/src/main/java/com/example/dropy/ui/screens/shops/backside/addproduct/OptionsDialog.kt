package com.example.dropy.ui.screens.shops.backside.addproduct

import com.example.dropy.R
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.dropy.ui.app.AppViewModel
import com.example.dropy.ui.app.navigation.shopsnavigation.ShopsBacksideNavigation
import com.example.dropy.ui.components.commons.SimpleText
import com.example.dropy.ui.screens.shops.backside.addshop.AddShopViewModel
import com.example.dropy.ui.theme.DropyYellow

@Composable
fun OptionsDialog(
    dismissme: (() -> Unit)? = null,
    show: Boolean = false,
    appViewModel: AppViewModel? = null,
    addShopViewModel: AddShopViewModel? = null,
    changeType: () -> Unit
) {

    if (show) {
        Dialog(onDismissRequest = { dismissme?.let { it() } }) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White, shape = RoundedCornerShape(15.dp))
                    .padding(start = 8.dp, end = 8.dp, bottom = 8.dp)
            ) {
                SimpleText(
                    text = "Choose option",
                    textSize = 18,
                    isBold = true,
                    verticalPadding = 16,
                    font = Font(R.font.axiformabold)
                )

                Row(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 15.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    optionItem(text = "Product Category", navigate = {
                        dismissme
                        addShopViewModel?.showDialog()
                    }, backgroundColor = DropyYellow)
                    optionItem(text = "Product", navigate = {
                        changeType()

                        appViewModel?.navigate(ShopsBacksideNavigation.ADD_PRODUCT)
                    })
                }


            }
        }
    }
}

@Composable
fun optionItem(text: String, navigate: () -> Unit, backgroundColor: Color = Color.LightGray) {
    Column(
        modifier = Modifier
            .clickable {
                navigate()
            }
            .background(backgroundColor.copy(.35f), shape = RoundedCornerShape(10.dp))
            .width(140.dp)
            .height(100.dp)
        /* .padding(start = 4.dp)*/,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        SimpleText(


            text = "Add\n" + text,
            textSize = 13,
            isBold = true,

            verticalPadding = 10,

            font = Font(R.font.axiformasemibold),

        )

    }
}

@Preview(showBackground = true)
@Composable
fun demo() {
    OptionsDialog(show = true) {

    }
}