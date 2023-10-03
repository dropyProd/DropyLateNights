package com.example.dropy.ui.components.shops.frontside.customer.orderprocess.customercart

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.dropy.network.models.GetCartResItem
import com.example.dropy.network.models.cart.OrderItem
import com.example.dropy.ui.components.shops.frontside.singleshop.shophome.BaseUrL
import com.example.dropy.ui.theme.LightContainerBackground

@Composable
fun CartItemsList(
    cartItems: List<GetCartResItem>,
    onAddCartItemClicked: (productId: Int) -> Unit,
    onRemoveCartItemClicked: (productId: Int) -> Unit,
    onDeleteCartItemClicked: (productId: Int) -> Unit,
) {
    Column(
//        modifier = Modifier.padding(8.dp)
    ) {
        cartItems.forEachIndexed { index, item ->
            item.product?.product_name?.let {
                item.quantity?.let { it1 ->
//                    item.item_total?.let { it2 ->
                        item.product.product_price?.let { it3 ->
                            CartItem(
                                productImageUrl = "${item.product?.product_cover_photo}",
                                productName = it,
                                cartItemsNumber = it1,
                                cartItemTotal = /*it2*/0,
                                onAddClicked = { quantity: Int, price: Int ->
                                    /* val edititem = item.copy(
                                                            cartItemsNumber = quantity,
                                                            cartItemTotal = price
                                                        )
                                                        onAddCartItemClicked(item, edititem)*/
                                    item.product.id?.let { it4 -> onAddCartItemClicked(it4) }
                                },
                                onRemoveClicked = { quantity: Int, price: Int ->
                                    /*     val edititem = item.copy(
                                                            cartItemsNumber = quantity,
                                                            cartItemTotal = price
                                                        )
                                                        onRemoveCartItemClicked(item, edititem)*/
                                    item.product.id?.let { it4 -> onRemoveCartItemClicked(it4) }

                                },
                                onDeleteClicked ={ quantity: Int, price: Int ->
                                    /*  val edititem = item.copy(
                                                            cartItemsNumber = quantity,
                                                            cartItemTotal = price
                                                        )
                                                        onDeleteCartItemClicked(edititem)*/

                                    item.product.id?.let { it4 -> onDeleteCartItemClicked(it4) }

                                },
                                backgroundColor = if (index % 2 == 0) {
                                    LightContainerBackground
                                } else {
                                    Color.White
                                },
                                cartItemPrice = it3
                            )
                        }
//                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun CartItemsListPreview() {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        CartItemsList(
            onAddCartItemClicked = {

            },
            onDeleteCartItemClicked = {

            },
            onRemoveCartItemClicked = {

            },
            cartItems = listOf()
        )
    }
}