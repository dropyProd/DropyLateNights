package com.example.dropy.ui.components.commons

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dropy.R
import com.example.dropy.network.models.deliverymethods.DeliveryMethodResponseItem
import com.example.dropy.network.models.getshopproductcategories.ProductCategory
import com.example.dropy.network.models.pools.RiderPoolsResponseItem
import com.example.dropy.network.models.shopCategories.ShopCategoriesResponseItem
import com.example.dropy.network.models.shopProductCategories.ShopProductCategoriesResponseItem
import com.example.dropy.ui.theme.LightBlue

@Composable
fun Dropdown(
    truckCapacities: List<String> = listOf(),
    onTruckCapacitySelect: ((String) -> Unit)? = null,
    items: List<ProductCategory> = listOf(),
    onSelect: ((ProductCategory, Int) -> Unit)? = null,
    onShopSelect: ((ShopCategoriesResponseItem, Int) -> Unit)? = null,
    type: String = "addProduct",
    shopcategoryitems: List<ShopCategoriesResponseItem> = listOf(),
    shoptypes: List<String> = listOf(),
    deliveryMethoditems: List<DeliveryMethodResponseItem> = listOf(),
    filteredsearchitems: List<String> = listOf(),
    poolitems: List<RiderPoolsResponseItem> = listOf(),
    productmeasurementitems: List<String> = listOf(),
    onMeasurementSelect: ((String) -> Unit)? = null,
    onDeliveryMethodSelect: ((DeliveryMethodResponseItem) -> Unit)? = null,
    onPoolsSelect: ((RiderPoolsResponseItem) -> Unit)? = null,
    onSearchFilterSelect: ((String) -> Unit)? = null,
    onShopTypeSelect: ((String) -> Unit)? = null,
    productCategory: String = "",
    refresh: Boolean = true
) {
    var expanded by remember { mutableStateOf(false) }
    Log.d("mkmkm", "Dropdown: ${items} ${productCategory}")

    val selectedIndex = when {
        productCategory != "" -> {
            remember {
                val res = mutableStateOf(0)
                Log.d("mkmkm", "Dropdown: ${items} ${productCategory}")
                items.forEachIndexed { index, productcategory ->
                    if (productcategory.category_name?.equals(productCategory) == true) {
                        res.value = index
                        Log.d("mkmkm", "Dropdown: ${res.value}")
                    }
                }

                mutableStateOf(res.value)
            }
        }
        items.isNotEmpty() -> {
            remember {
                mutableStateOf(0)
            }
        }
        deliveryMethoditems.isNotEmpty() -> {
            remember {
                mutableStateOf(0)
            }
        }
        poolitems.isNotEmpty() -> {
            remember {
                mutableStateOf(0)
            }
        }
        filteredsearchitems.isNotEmpty() -> {
            remember {
                mutableStateOf(0)
            }
        }

        shopcategoryitems.isNotEmpty() -> {
            remember {
                mutableStateOf(0)
            }
        }
        productmeasurementitems.isNotEmpty() -> {
            remember {
                mutableStateOf(0)
            }
        }
        shoptypes.isNotEmpty() -> {
            remember {
                mutableStateOf(0)
            }
        }
        truckCapacities.isNotEmpty() -> {
            remember {
                mutableStateOf(0)
            }
        }
        else -> {
            null
        }
    }

    Log.d("GGGTAG", "Dropdown: $type  $shopcategoryitems")
    Box(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.TopStart)
    ) {
        Box(
            modifier = Modifier
                .clickable(onClick = { expanded = true })
                .height(48.dp)
                .clip(RoundedCornerShape(8.dp))
                .border(1.dp, Color.Black, RoundedCornerShape(8.dp))
                .background(Color.White),
            contentAlignment = Alignment.CenterStart
        ) {
            when {
                selectedIndex != null -> {
                    Text(
                        text = when {
                            type.equals("addProduct") -> if (!items.isEmpty()) items[selectedIndex.value].category_name else ""
                            type.equals("measurement") -> productmeasurementitems[selectedIndex.value]
                            type.equals("ridermethods") -> deliveryMethoditems[selectedIndex.value].method_name
                            type.equals("riderpools") -> poolitems[selectedIndex.value].name
                            type.equals("searchitems") -> filteredsearchitems[selectedIndex.value]
                            type.equals("shoptype") -> shoptypes[selectedIndex.value]
                            type.equals("waterTruck") -> truckCapacities[selectedIndex.value]
                            else -> shopcategoryitems[selectedIndex.value].category_name
                        }.toString(),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 8.dp),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Black,
                        fontFamily = FontFamily(Font(R.font.axiformablack))
                    )
                }
                else -> {
                    Text(
                        text = "Select ...",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 8.dp),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Black,
                        fontFamily = FontFamily(Font(R.font.axiformablack))
                    )
                }
            }
            IconButton(
                onClick = { expanded = true },
                modifier = Modifier
                    .align(Alignment.CenterEnd)
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowDropDown,
                    contentDescription = "dropdown icon",
                    modifier = Modifier
                        .size(32.dp)
                )

            }
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
               // .padding(8.dp)
                .wrapContentWidth()
                .heightIn(10.dp, 200.dp)
                .background(Color.White)
                .padding(horizontal = 7.dp)
        ) {
            when {
                type.equals("addProduct") -> {
                    items.forEachIndexed { index, dropdownText ->
                        DropdownMenuItem(
                            onClick = {
                                selectedIndex?.value = index
                                expanded = false
                                if (onSelect != null) {
                                    onSelect(dropdownText, index)
                                }
                            }
                        ) {
                            dropdownText.category_name?.let {
                                Text(
                                    text = it,
                                    fontFamily = FontFamily(Font(R.font.axiformasemibold))
                                )
                            }
                        }

                    }
                }
                type.equals("measurement") -> {
                    productmeasurementitems.forEachIndexed { index, dropdownText ->
                        DropdownMenuItem(
                            onClick = {
                                selectedIndex?.value = index
                                expanded = false
                                if (onMeasurementSelect != null) {
                                    onMeasurementSelect(dropdownText)
                                }
                            }
                        ) {
                            Text(
                                text = dropdownText,
                                fontFamily = FontFamily(Font(R.font.axiformasemibold))
                            )
                        }
                    }
                }
                type.equals("ridermethods") -> {
                    deliveryMethoditems.forEachIndexed { index, dropdownText ->
                        DropdownMenuItem(
                            onClick = {
                                selectedIndex?.value = index
                                expanded = false
                                if (onDeliveryMethodSelect != null) {
                                    onDeliveryMethodSelect(dropdownText)
                                }
                            }
                        ) {
                            dropdownText.method_name?.let {
                                Text(
                                    text = it,
                                    fontFamily = FontFamily(Font(R.font.axiformasemibold))
                                )
                            }
                        }
                    }
                }
                type.equals("riderpools") -> {
                    poolitems.forEachIndexed { index, dropdownText ->
                        DropdownMenuItem(
                            onClick = {
                                selectedIndex?.value = index
                                expanded = false
                                if (onPoolsSelect != null) {
                                    onPoolsSelect(dropdownText)
                                }
                            }
                        ) {
                            Text(
                                text = dropdownText.name.toString(),
                                fontFamily = FontFamily(Font(R.font.axiformasemibold))
                            )
                        }
                    }
                }
                type.equals("searchitems") -> {
                    filteredsearchitems.forEachIndexed { index, dropdownText ->
                        DropdownMenuItem(
                            onClick = {
                                selectedIndex?.value = index
                                expanded = false
                                if (onSearchFilterSelect != null) {
                                    onSearchFilterSelect(dropdownText)
                                }
                            }
                        ) {
                            Text(
                                text = dropdownText.toString(),
                                fontFamily = FontFamily(Font(R.font.axiformasemibold))
                            )
                        }
                    }
                }
                type.equals("shoptype") -> {
                    shoptypes.forEachIndexed { index, dropdownText ->
                        DropdownMenuItem(
                            onClick = {
                                selectedIndex?.value = index
                                expanded = false
                                if (onShopTypeSelect != null) {
                                    onShopTypeSelect(dropdownText)
                                }
                            }
                        ) {
                            Text(
                                text = dropdownText.toString(),
                                fontFamily = FontFamily(Font(R.font.axiformasemibold))
                            )
                        }
                    }
                }
                type.equals("waterTruck") -> {
                    truckCapacities.forEachIndexed { index, dropdownText ->
                        DropdownMenuItem(
                            onClick = {
                                selectedIndex?.value = index
                                expanded = false
                                if (onTruckCapacitySelect != null) {
                                    onTruckCapacitySelect(dropdownText)
                                }
                            }
                        ) {
                            Text(
                                text = dropdownText.toString(),
                                fontFamily = FontFamily(Font(R.font.axiformasemibold))
                            )
                        }
                    }
                }
                else -> {
                    shopcategoryitems.forEachIndexed { index, dropdownText ->
                        DropdownMenuItem(
                            onClick = {
                                selectedIndex?.value = index
                                expanded = false
                                if (onShopSelect != null) {
                                    onShopSelect(dropdownText, index)
                                }
                            }
                        ) {
                            dropdownText.category_name?.let {
                                Text(
                                    text = it,
                                    fontFamily = FontFamily(Font(R.font.axiformasemibold))
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DropdownPreview() {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
    ) {
        Dropdown(items = listOf(), onSelect = { t, i ->

        }, shopcategoryitems = listOf(), onShopSelect = { _, _ ->
        })
    }
}
