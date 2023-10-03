package com.example.dropy.ui.screens.review

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.navOptions
import com.example.dropy.R
import com.example.dropy.ui.app.AppDestinations
import com.example.dropy.ui.components.payment.PaymentDialog
import com.example.dropy.ui.screens.rider.IncomingJobViewModel
import kotlinx.coroutines.delay

@Composable
fun ReviewThanksScreen(
    navController: NavController? = null,
    incomingJobViewModel: IncomingJobViewModel? = null
) {

    /* LaunchedEffect(key1 = true, block = {
         for(i in 0 until 3) {

             if (i == 2) {
                 navController?.navigate(AppDestinations.APP_HOME){
                     navOptions { // Use the Kotlin DSL for building NavOptions
                         anim {
                             enter = android.R.animator.fade_in
                             exit = android.R.animator.fade_out
                         }
                     }
                 }
             }
             delay(1000)
         }
     })
 */

    Column(
        modifier = Modifier
            .clickable {
//                incomingJobViewModel?.resetReviewRoute()
                navController?.navigate(AppDestinations.APP_HOME)/*{
                    navOptions { // Use the Kotlin DSL for building NavOptions
                        anim {
                            enter = android.R.animator.fade_in
                            exit = android.R.animator.fade_out
                        }
                    }
                }*/
            }
            .fillMaxSize()
            .background(color = Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Column(
            verticalArrangement = Arrangement.spacedBy(33.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "THANK YOU",
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 30.sp,
//                    fontWeight = FontWeight.ExtraBold,
                    fontFamily = FontFamily(Font(R.font.axiformaheavy)),
                    textAlign = TextAlign.Center,
                    letterSpacing = (-1.44).sp,
                    lineHeight = 27.sp
                ), modifier = Modifier.fillMaxWidth()
            )
            Image(
                painter = painterResource(id = R.drawable.thankrobot),/*rafiki*/
                contentDescription = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(471.dp),
                contentScale = ContentScale.FillWidth
            )

            Text(
                text = "Thank you for \nyour review",
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 16.sp,
//                    fontWeight = FontWeight.ExtraBold,
                    fontFamily = FontFamily(Font(R.font.axiformabold)),
                    textAlign = TextAlign.Center,
                    letterSpacing = (-0.86).sp,
                    lineHeight = 27.sp
                ), modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Preview
@Composable
fun demol(){
    ReviewThanksScreen()
}