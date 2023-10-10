package com.example.dropy.ui.screens.workDiary

import android.os.Build
import android.view.LayoutInflater
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.dropy.R
import com.example.dropy.network.models.GetIndividualOrders.GetIndividualOrdersResItem
import com.example.dropy.ui.app.AppUiState
import com.example.dropy.ui.components.shops.shopscommons.ClippedHeader
import devs.mulham.horizontalcalendar.HorizontalCalendar
import devs.mulham.horizontalcalendar.HorizontalCalendarListener
import java.util.*


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun WorkDiaryContent(
    workDiaryUiState: WorkDiaryUiState,
    navigate: () -> Unit,
    appUiState: AppUiState
) {
    val context = LocalContext.current
    /** end after 1 month from now */
    /** end after 1 month from now  */
    val endDate: Calendar = remember {
        Calendar.getInstance()
    }
    endDate.add(Calendar.MONTH, 1)

    /** start before 1 month from now */
    /** start before 1 month from now  */
    val startDate: Calendar = remember {
        Calendar.getInstance()
    }
    startDate.add(Calendar.MONTH, -1)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {

        ClippedHeader(title = "WORK PLAN DIARY", modifier = Modifier.padding(top = 22.dp))

        AndroidView(modifier = Modifier.padding(top = 21.dp),
            factory = { context ->
                val view = LayoutInflater.from(context).inflate(R.layout.calendar, null, false)
                val horizontalCalendar: HorizontalCalendar =
                    HorizontalCalendar.Builder(view, R.id.calendarView)
                        .startDate(startDate.getTime())
                        .endDate(endDate.getTime())
                        .datesNumberOnScreen(5)   // Number of Dates cells shown on screen (Recommended 5)
                        .dayNameFormat("EEE")      // WeekDay text format
                        .dayNumberFormat("dd")    // Date format
                        .monthFormat("MMM")      // Month format
                        .showDayName(true)      // Show or Hide dayName text
                        .showMonthName(true)      // Show or Hide month text
                        /*.textColor(
                            0xFFCCCCCC,
                            Color.LightGray
                        )  */  // Text color for none selected Dates, Text color for selected Date.
//                        .selectedDateBackground(Color.TRANSPARENT)  // Background color of the selected date cell.
//                        .selectorColor(Color.RED)   // Color of the selection indicator bar (default to colorAccent).
                        .defaultSelectedDate(Date())  // Date to be seleceted at start (default to Today)
                        .build()
                horizontalCalendar.setCalendarListener(object : HorizontalCalendarListener() {
                    override fun onDateSelected(date: Date?, position: Int) {
                        //do something
                        Toast.makeText(context, "${date?.date}", Toast.LENGTH_SHORT).show()
                    }
                })
                // do whatever you want...
                view // return the view
            },
            update = { view ->
                // Update the view
            }
        )

        LazyColumn(modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(start = 15.dp, end = 14.dp)
            .padding(top = 21.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp), content = {
                itemsIndexed(workDiaryUiState.orders) { index, item ->
                    val backgroundColor = if (index % 2 == 0)
                        Color.Transparent
                    else Color(0xFFF5F5F5)

                    item.tasks.forEach {
                        if (it.truck.id.equals(appUiState.activeProfile?.id)) {
                            if (!it.id.equals(""))
                                truckOrderItem(
                                    navigate = { navigate() },
                                    color = backgroundColor,
                                    getIndividualOrdersRes = item,
                                    workDiaryUiState = workDiaryUiState
                                )
                        }
                    }

                }
            })

    }
}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun truckOrderItem(
    navigate: () -> Unit,
    color: Color,
    getIndividualOrdersRes: GetIndividualOrdersResItem,
    workDiaryUiState: WorkDiaryUiState
) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .height(100.dp)
        .background(color = color, shape = RoundedCornerShape(12.dp))
        .border(width = 1.dp, color = Color(0xFFDEDEDE), shape = RoundedCornerShape(12.dp))
        .clickable { navigate() }
    ) {

        Column(modifier = Modifier.padding(top = 9.dp)) {
            Image(
                painter = painterResource(id = R.drawable.shop1),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(start = 16.dp)
                    .width(50.dp)
                    .height(50.dp)
                    .clip(CircleShape)
            )

            Text(
                text = getIndividualOrdersRes.customer.first_name + " " + getIndividualOrdersRes.customer.last_name,
                fontSize = 9.sp,
//                        fontWeight = FontWeight.ExtraBold,
                fontFamily = FontFamily(
                    Font(R.font.axiformablack)
                ),
                letterSpacing = (-0.43).sp,
                lineHeight = 17.sp,
                color = Color.Black,
                modifier = Modifier.padding(top = 10.dp, start = 22.dp)
            )
        }

        Column(modifier = Modifier.padding(top = 16.dp)) {
            Text(
                text = "Order NO #${getIndividualOrdersRes.tracking_id}",
                fontSize = 11.sp,
//                        fontWeight = FontWeight.ExtraBold,
                fontFamily = FontFamily(
                    Font(R.font.axiformablack)
                ),
                letterSpacing = (-0.53).sp,
                lineHeight = 21.sp,
                color = Color.Black,
                modifier = Modifier.padding(start = 18.dp)
            )

            Row(
                modifier = Modifier
                    .padding(top = 1.dp, start = 18.dp)
                    .wrapContentWidth()
                    .height(18.dp)
                    .background(
                        color = Color(0xFF979797),
                        shape = RoundedCornerShape(9.dp)
                    )
                    .padding(horizontal = 6.dp)
                /*.border(
                    width = 1.dp,
                    color = Color(0xFFD1D1D1),
                    shape = RoundedCornerShape(7.dp)
                )*/
            ) {
                Text(
                    text = if (getIndividualOrdersRes.delivery_status.equals("P")) "NOT STARTED" else if (getIndividualOrdersRes.delivery_status.equals(
                            "S"
                        )
                    ) "ON THE WAY" else "DELIVERED",
                    fontSize = 7.sp,
//                        fontWeight = FontWeight.ExtraBold,
                    fontFamily = FontFamily(
                        Font(R.font.axiformablack)
                    ),
                    letterSpacing = (-0.34).sp,
                    lineHeight = 13.sp,
                    color = Color.White,
                    modifier = Modifier.padding(
                        start = 8.dp,
                        end = 9.dp,
                        top = 5.dp
//                        bottom = 1.dp
                    )
                )
            }

            Row(
                modifier = Modifier
                    .padding(top = 8.dp, start = 18.dp)
                    .wrapContentWidth()
                    .height(18.dp)
                    .background(
                        color = Color(0xFFC2F8FF),
                        shape = RoundedCornerShape(9.dp)
                    )
                    .padding(horizontal = 6.dp)
                /*.border(
                    width = 1.dp,
                    color = Color(0xFFD1D1D1),
                    shape = RoundedCornerShape(7.dp)
                )*/
            ) {
                Text(
                    text = if (getIndividualOrdersRes.delivery_status.equals("P")) "NOT STARTED" else if (getIndividualOrdersRes.delivery_status.equals(
                            "S"
                        )
                    ) "ON THE WAY" else "DELIVERED",
                    fontSize = 9.sp,
//                        fontWeight = FontWeight.ExtraBold,
                    fontFamily = FontFamily(
                        Font(R.font.axiformaextrabold)
                    ),
                    letterSpacing = (-0.43).sp,
                    lineHeight = 17.sp,
                    color = Color.Black,
                    modifier = Modifier.padding(
                        start = 9.dp,
                        end = 5.dp,
                        top = 5.dp,
                        bottom = 4.dp
                    )
                )
            }
        }

        Column(
            modifier = Modifier
                .padding(top = 20.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.End
        ) {

                /*val date = remember{
                    mutableStateOf("")
                }
                getIndividualOrdersRes.timestamp.forEachIndexed { index, time ->
                    if (index in 0..9){
                        date.value += time
                    }
                }
                val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
                val newDate = formatter.parse(date.value)
                val formatterNew = DateTimeFormatter.ofPattern("dd/MMM/yy")
                val formatDate = formatterNew.format(newDate)
*/
            Text(
                text = "formatDate",
                fontSize = 8.sp,
//                        fontWeight = FontWeight.ExtraBold,
                fontFamily = FontFamily(
                    Font(R.font.axiformamedium)
                ),
                letterSpacing = (-0.38).sp,
                lineHeight = 15.sp,
                color = Color(0xFF979797),
                modifier = Modifier.padding(end = 7.dp)
            )
            Text(
                text = workDiaryUiState.truckDetails?.capacity.toString() + "LT",
                fontSize = 17.sp,
//                        fontWeight = FontWeight.ExtraBold,
                fontFamily = FontFamily(
                    Font(R.font.axiformaheavy)
                ),
                letterSpacing = (-0.82).sp,
                lineHeight = 32.sp,
                color = Color.Black,
                modifier = Modifier.padding(end = 8.dp, top = 5.dp)
            )

            Row(
                modifier = Modifier
                    .padding(top = 17.dp, end = 7.dp)
                    .width(68.dp)
                    .height(18.dp)
                    .background(
                        color = Color(0xFF02CBE3),
                        shape = RoundedCornerShape(12.dp)
                    )
                /*.border(
                    width = 1.dp,
                    color = Color(0xFFD1D1D1),
                    shape = RoundedCornerShape(7.dp)
                )*/
            ) {
                Text(
                    text = "ETA 12 HRS",
                    fontSize = 10.sp,
//                        fontWeight = FontWeight.ExtraBold,
                    fontFamily = FontFamily(
                        Font(R.font.axiformaextrabold)
                    ),
                    letterSpacing = (-0.48).sp,
                    lineHeight = 19.sp,
                    color = Color.White,
                    modifier = Modifier.padding(
                        start = 8.dp,
                        end = 4.dp,
                        top = 4.dp,
                        bottom = 1.dp
                    )
                )
            }
        }

    }
}