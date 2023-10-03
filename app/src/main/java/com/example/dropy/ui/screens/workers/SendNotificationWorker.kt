package com.example.dropy.ui.screens.workers

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.dropy.ui.screens.notification.NotificationData
import com.example.dropy.ui.screens.notification.PushNotification
import com.example.dropy.ui.screens.notification.RetrofitInstance
import com.google.firebase.messaging.FirebaseMessaging
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SendNotificationWorker(appContext: Context, workerParams: WorkerParameters) :
    Worker(appContext, workerParams) {

    companion object {
        var text: String? = null
        var topic: String? = null
        //var type: String? = null
        var userName: String? = null
    }


    override fun doWork(): Result {

        // Do the work here--in this case, upload the images.
        //  uploadImages()
//getting sent data
/*        val user =
            inputData.getString("user") ?: "dd"*//*return Result.failure()*/



        try {
      /*      Log.d("TAGZ", "doWork: $user")*/

            val title = "Process Order"
            val message =
                "${userName} Order has been processed"
            //OPIC = "/topics/${homeMainViewModel.chamaDetails.value?.chamaName!!}"
            FirebaseMessaging.getInstance().subscribeToTopic(topic!!).also {
                PushNotification(
                    NotificationData(title = title!!, message = message),
                    topic!! //limits which users to get the notification
                ).also {
                    sendNotification(it)
                }
            }

            // Indicate whether the work finished successfully with the Result
            return Result.success()
        } catch (e: Exception) {
            return Result.retry()
        }


    }



    fun sendNotification(notification: PushNotification) =
        CoroutineScope(Dispatchers.IO).launch {
            try {

                val response = RetrofitInstance.api.postNotification(notification = notification)

                if (response.isSuccessful) {
                    Log.d("TAG", "sendNOtification: ${Gson().toJson(response)}")
                } else {
                    Log.d("TAG", "sendNOtification: ${response.errorBody().toString()}")
                }

            } catch (e: Exception) {
                Log.e("TAG", "sendNOtification: ${e.toString()} ")
            }
        }
}
