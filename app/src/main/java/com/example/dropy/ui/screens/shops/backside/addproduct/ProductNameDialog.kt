package com.example.dropy.ui.screens.shops.backside.addproduct

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.dropy.ui.app.AppViewModel
import com.example.dropy.ui.components.commons.SimpleText
import com.example.dropy.ui.components.commons.TotallyRoundedButton
import com.example.dropy.ui.screens.apphome.AppHomePageViewModel
import com.example.dropy.ui.screens.shops.backside.addshop.AddShopViewModel
import com.example.dropy.ui.theme.DropyYellow
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun ProductNameDialog(
    show: Boolean,
    dismiss: () -> Unit,
    dismissme: (() -> Unit)? = null,
    addProductViewModel: AddProductViewModel,
    addShopViewModel: AddShopViewModel,
    addProductCategory: () -> Unit,
    appHomePageViewModel: AppHomePageViewModel, appViewModel: AppViewModel
) {

    val appUiState by appViewModel.appUiState.collectAsState()
    val scope = rememberCoroutineScope()
    LaunchedEffect(key1 = show, block = {
        if (show) {
            for (i in 1..6) {

                if (i == 3) {
                    appHomePageViewModel.getAllShops()
                    //appViewModel.getUserDetails(firebaseUid = appUiState.firebaseUid!!)
                }

                Log.d("ddeftrfcd", "ProductNameDialog: Second $i")
                delay(1400)

            }
        }
    })

    if (show) {
        Dialog(onDismissRequest = { dismissme?.let { it() } }) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, end = 10.dp)
                    .background(Color.White, shape = RoundedCornerShape(20.dp))
            ) {
                SimpleText(


                    text = "Shop Product Category",
                    textSize = 18,
                    isBold = true,
                    verticalPadding = 16,
                    padding = 10,
                    textAlign = TextAlign.Center





                )
                OutlinedTextField(
                    value = addProductViewModel.productcategoryname.value,
                    onValueChange = addProductViewModel::onProductcategorynameChanged,
                    modifier = Modifier
                        .padding(8.dp),
                    singleLine = true,
                    label = {
                        Text(text = "Product category name")
                    }
                )
                Column(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    verticalArrangement = Arrangement.spacedBy(7.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    TotallyRoundedButton(
                        buttonText = "add product category",
                        backgroundColor = DropyYellow,
                        action = {
                            //  scope.launch { appHomePageViewModel.getAllShops() }
                            addProductViewModel.clearValues()
                            addProductCategory()
                        },
                        widthFraction = 0.7
                    )

                    TotallyRoundedButton(
                        buttonText = "add product",
                        backgroundColor = Color.Black,
                        action = { dismiss() },
                        widthFraction = 0.7,
                        textColor = Color.White
                    )
                }
            }
        }
    }
}
/*

@Preview(showBackground = true)
@Composable
fun bemo(){
    ProductNameDialog(show = true, dismiss = { */
/*TODO*//*
 }, addProductViewModel = hiltViewModel())
}*/
