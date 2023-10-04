package com.example.dropy.ui.screens.waterSelectDate

import android.os.Build
import android.view.LayoutInflater
import android.widget.CalendarView
import android.widget.CalendarView.OnDateChangeListener
import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.dropy.R
import com.example.dropy.ui.screens.tankerBorehole.TankerBoreholeUiState
import java.time.format.DateTimeFormatter
import java.util.*

fun formatTimee(text: String): String {
    val formatDate = mutableStateOf("")
    try {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val formatter =
                DateTimeFormatter.ofPattern("dd/MM/yyyy")

            val newDate = formatter.parse(text)
            val formatterNew = DateTimeFormatter.ofPattern("dd, MMM, yyyy")
            formatDate.value = formatterNew.format(newDate)
        } else {
            TODO("VERSION.SDK_INT < O")
        }
    } catch (e: Exception) {

    }
    return formatDate.value
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WaterSelectDateContent(
    submitClicked: () -> Unit,
    selectedDate: (String) -> Unit,
    selectedTimeSlot: (String) -> Unit,
    tankerBoreholeUiState: TankerBoreholeUiState
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(660.dp)
            .padding(start = 15.dp, end = 14.dp)
            .background(color = Color(0xFFFFFFFF), shape = RoundedCornerShape(31.dp))
            .border(width = 1.dp, color = Color(0xFFDEDEDE), shape = RoundedCornerShape(31.dp))
    ) {

//        val state = rememberDatePickerState(initialDisplayMode = DisplayMode.Input)
//        DatePicker(state = state, modifier = Modifier.padding(16.dp), colors = DatePickerDefaults.colors(containerColor = Color.Transparent))
//
//        Log.d("hg", "WaterSelectDateContent: "+state.selectedDateMillis)

        AndroidView(modifier = Modifier.padding(top = 20.dp),
            factory = { context ->
                val view = LayoutInflater.from(context).inflate(R.layout.datepicker, null, false)
                val calendar = view.findViewById<CalendarView>(R.id.calendarVieww)

                calendar.setOnDateChangeListener(OnDateChangeListener { calendarView, i, i1, i2 ->
                    val msg = "Selected date Day: " + i2 + " Month : " + (i1 + 1) + " Year " + i
//                    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
                    selectedDate("${i2}/${(i1 + 1)}/${i}")
                })
                // do whatever you want...
                view // return the view
            },
            update = { view ->
                // Update the view
            }
        )

        Text(
            text = "choose time slot",
            color = Color(0xFF979797),
            fontSize = 9.sp,
//            fontWeight = FontWeight.SemiBold,
            fontFamily = FontFamily(Font(R.font.axiformaheavy)),
            letterSpacing = (-0.43).sp,
            lineHeight = 17.sp,
            modifier = Modifier
                .padding(top = 6.dp, start = 45.dp, end = 42.dp)
        )
        Row(
            horizontalArrangement = Arrangement.spacedBy(20.dp),
            modifier = Modifier
                .padding(top = 6.dp, start = 41.dp, end = 42.dp)
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState())
        ) {
            Row(
                modifier = Modifier
                    .padding(top = 13.dp)
                    .width(60.dp)
                    .height(21.dp)
                    .background(color = Color(0xFFFFFFFF), RoundedCornerShape(17.dp))
                    .border(
                        width = 1.dp,
                        color = Color(0xFFDEDEDE),
                        shape = RoundedCornerShape(17.dp)
                    )
                    .clickable {
                        selectedTimeSlot("9:00 am")
                    },
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "9:00 AM",
                    color = Color.Black,
                    fontSize = 9.sp,
//            fontWeight = FontWeight.SemiBold,
                    fontFamily = FontFamily(Font(R.font.axiformablack)),
                    letterSpacing = (-0.43).sp,
                    lineHeight = 17.sp
                )
            }
            Row(
                modifier = Modifier
                    .padding(top = 13.dp)
                    .width(60.dp)
                    .height(21.dp)
                    .background(color = Color(0xFFFFFFFF), RoundedCornerShape(17.dp))
                    .border(
                        width = 1.dp,
                        color = Color(0xFFDEDEDE),
                        shape = RoundedCornerShape(17.dp)
                    )
                    .clickable {
                        selectedTimeSlot("12:00 pm")
                    },
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "12:00 PM",
                    color = Color.Black,
                    fontSize = 9.sp,
//            fontWeight = FontWeight.SemiBold,
                    fontFamily = FontFamily(Font(R.font.axiformablack)),
                    letterSpacing = (-0.43).sp,
                    lineHeight = 17.sp
                )
            }

            Row(
                modifier = Modifier
                    .padding(top = 13.dp)
                    .width(60.dp)
                    .height(21.dp)
                    .background(color = Color(0xFF02CBE3), RoundedCornerShape(17.dp))
                    .border(
                        width = 1.dp,
                        color = Color(0xFFDEDEDE),
                        shape = RoundedCornerShape(17.dp)
                    )
                    .clickable {
                        selectedTimeSlot("3:00 pm")
                    },
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "3:00 PM",
                    color = Color.White,
                    fontSize = 9.sp,
//            fontWeight = FontWeight.SemiBold,
                    fontFamily = FontFamily(Font(R.font.axiformablack)),
                    letterSpacing = (-0.43).sp,
                    lineHeight = 17.sp
                )
            }
            Row(
                modifier = Modifier
                    .padding(top = 13.dp)
                    .width(60.dp)
                    .height(21.dp)
                    .background(color = Color(0xFFFFFFFF), RoundedCornerShape(17.dp))
                    .border(
                        width = 1.dp,
                        color = Color(0xFFDEDEDE),
                        shape = RoundedCornerShape(17.dp)
                    )
                    .clickable {
                        selectedTimeSlot("5:00 pm")
                    },
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "5:00 PM",
                    color = Color.Black,
                    fontSize = 9.sp,
//            fontWeight = FontWeight.SemiBold,
                    fontFamily = FontFamily(Font(R.font.axiformablack)),
                    letterSpacing = (-0.43).sp,
                    lineHeight = 17.sp
                )
            }
        }

        Text(
            text = "selected date",
            color = Color(0xFF979797),
            fontSize = 9.sp,
//            fontWeight = FontWeight.SemiBold,
            fontFamily = FontFamily(Font(R.font.axiformaheavy)),
            letterSpacing = (-0.43).sp,
            lineHeight = 17.sp,
            modifier = Modifier
                .padding(top = 45.dp, start = 45.dp, end = 42.dp)
        )

        Text(
            text = formatTimee(tankerBoreholeUiState.selectedDate),
            color = Color.Black,
            fontSize = 14.sp,
//            fontWeight = FontWeight.SemiBold,
            fontFamily = FontFamily(Font(R.font.axiformaheavy)),
            letterSpacing = (-0.67).sp,
            lineHeight = 27.sp,
            modifier = Modifier
                .padding(top = 14.dp, start = 45.dp, end = 42.dp)
        )

        Row(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 53.dp)
                .width(133.dp)
                .height(36.dp)
                .background(color = Color(0xFFAFF5FE), RoundedCornerShape(42.dp))
                .border(width = 1.dp, color = Color(0x57707070), RoundedCornerShape(42.dp))
                .clickable { submitClicked() },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            androidx.compose.material.Text(
                text = "SUBMIT",
                color = Color.Black,
                fontSize = 10.sp,
//                        fontWeight = FontWeight.SemiBold,
                fontFamily = FontFamily(Font(R.font.axiformaheavy)),
                letterSpacing = (-0.48).sp,
            )
        }

    }
}

@Preview
@Composable
fun demo() {
    WaterSelectDateContent(
        submitClicked = {},
        selectedDate = {},
        selectedTimeSlot = {},
        tankerBoreholeUiState = TankerBoreholeUiState()
    )
}