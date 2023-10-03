package com.example.dropy.ui.utils

import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.core.app.NotificationCompat
import com.example.dropy.MainActivity
import com.example.dropy.R
import com.example.dropy.ui.app.AppViewModel
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach


class LocationService : Service() {

    private val serviceScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
    private lateinit var locationClient: LocationClient
    /*  val viewmodel: MutableState<AppViewModel?> = mutableStateOf(null)

      fun setAppViewModel(appViewModel: AppViewModel){
          viewmodel.value = appViewModel
      }*/


    public interface passRandomData {
        // fun setViewmodel(appViewModel: AppViewModel)
        fun setRandom(appViewModel: AppViewModel)
    }

    class interfaceImpl : passRandomData {
        override fun setRandom(apppViewModel: AppViewModel) {
            Log.d("ssddda", "setRandom: $appViewwModel")
            appViewwModel.value = apppViewModel
        }

    }

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        locationClient = DefaultLocationClient(
            applicationContext,
            LocationServices.getFusedLocationProviderClient(applicationContext),
             appViewModel = appViewwModel.value
        )


    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when (intent?.action) {
            ACTION_START -> start()
            ACTION_STOP -> stop()
        }
        return super.onStartCommand(intent, flags, startId)
    }

    public fun start() {
    /*    val notification = NotificationCompat.Builder(this, "location")
            .setContentTitle("Tracking location...")
            .setContentText("Location: null")
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setOngoing(true)*/

        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        locationClient
            .getLocationUpdates(10000L)
            .catch { e -> e.printStackTrace() }
            .onEach { location ->
                val lat = location.latitude.toString().takeLast(3)
                val long = location.longitude.toString().takeLast(3)

                Log.d("koko", "start: $lat >>> $long")
           /*     val updatedNotification = notification.setContentText(
                    "Location: ($lat, $long)"
                )
                // viewmodel.value?.setYourLocale(currentLocation.value!!)
                notificationManager.notify(1, updatedNotification.build())*/
            }
            .launchIn(serviceScope)

      //  startForeground(1, notification.build())
    }

    private fun stop() {
        stopForeground(true)
        stopSelf()
    }

    override fun onDestroy() {
        super.onDestroy()
        serviceScope.cancel()
    }

    companion object {
        const val ACTION_START = "ACTION_START"
        const val ACTION_STOP = "ACTION_STOP"
        val appViewwModel: MutableState<AppViewModel?> = mutableStateOf(null)
    }


}