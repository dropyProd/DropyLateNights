package com.example.dropy.di

import android.Manifest
import android.app.Activity
import android.app.Application

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.core.app.ActivityCompat
import com.example.dropy.network.models.*
import com.example.dropy.network.models.GetIndividualOrders.GetIndividualOrdersResItem
import com.example.dropy.network.models.getTruckDrivers.GetTruckDriversResItem
import com.example.dropy.network.models.getWaterPoints.GetWaterPointsResItem
import com.example.dropy.network.models.getWaterTrucks.GetTrucksResItem
import com.example.dropy.network.models.getWaterVendors.GetWaterVendorsResItem
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class DropyApp : Application() {

    public val token = mutableStateOf("")
    public val id = mutableStateOf("")
    public val shopId = mutableStateOf("")
    public val incomingShopId = mutableStateOf("")
    public val incomingCustomerId = mutableStateOf("")
    public val myUserDetailsRes: MutableState<UserDetailRes?> = mutableStateOf(null)
    public val cartId: MutableState<Int?> = mutableStateOf(null)
    public val selectedOrder: MutableState<PaymentReq?> = mutableStateOf(null)
    public val selectedIncomingOrder: MutableState<ShopOrdersResponseItem?> = mutableStateOf(null)
    public val incomingOrderId: MutableState<Int?> = mutableStateOf(null)
    public val cartList: MutableList<GetCartResItem> = mutableListOf()
    public val shop: MutableState<ShopsResponseNewItem?> = mutableStateOf(null)
    val shopOrders: MutableList<ShopOrdersResponseItem> = mutableListOf()
    val myShopsList: MutableList<ShopsResponseNewItem> = mutableListOf()
    public val myProfile : MutableState<UserDetailRes?> = mutableStateOf(null)
    val individualOrders : MutableList<GetIndividualOrdersResItem> = mutableListOf()
    val waterpoints : MutableList<GetWaterPointsResItem> = mutableListOf()
    val myWaterpoints : MutableList<GetWaterPointsResItem> = mutableListOf()
    val waterTruckDrivers : MutableList<GetTruckDriversResItem> = mutableListOf()
    val waterTrucks : MutableList<GetTrucksResItem> = mutableListOf()
    val myWaterTrucks : MutableList<GetTrucksResItem> = mutableListOf()
    val myWaterVendors : MutableList<GetWaterVendorsResItem> = mutableListOf()
    val myDeviceId : MutableState<String> = mutableStateOf("")

    val currentLocation: MutableState<Location?> = mutableStateOf(null)
    val locationByGps: MutableState<Location?> = mutableStateOf(null)
    val locationByNetwork: MutableState<Location?> = mutableStateOf(null)


    val gpsLocationListener: LocationListener = object : LocationListener {
        override fun onLocationChanged(location: Location) {
            locationByGps.value= location
//            Toast.makeText(applicationContext, "${locationByGps.latitude} >>> ${locationByGps.longitude}", Toast.LENGTH_SHORT).show()

        }

        override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {}
        override fun onProviderEnabled(provider: String) {}
        override fun onProviderDisabled(provider: String) {}
    }
    //------------------------------------------------------//
    val networkLocationListener: LocationListener = object : LocationListener {
        override fun onLocationChanged(location: Location) {
            locationByNetwork.value= location
//            Toast.makeText(applicationContext, "${locationByNetwork.latitude} >>> ${locationByNetwork.longitude}", Toast.LENGTH_SHORT).show()
        }

        override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {}
        override fun onProviderEnabled(provider: String) {}
        override fun onProviderDisabled(provider: String) {}
    }


    override fun onCreate() {
        super.onCreate()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                "location",
                "Location",
                NotificationManager.IMPORTANCE_LOW
            )
            val notificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    fun getCurrentLocation(context: Context){
        val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val isGpsEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
        val hasNetwork = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
        //Toast.makeText(this, "$isGpsEnabled", Toast.LENGTH_SHORT).show()
        /*  if (!isGpsEnabled) {
              val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
              startActivity(intent)
          }*/
        if (isGpsEnabled) {
            if (ActivityCompat.checkSelfPermission(
                    context,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                    context,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.

                ActivityCompat.requestPermissions(
                    context as Activity,
                    arrayOf(
                        android.Manifest.permission.ACCESS_FINE_LOCATION,
                        android.Manifest.permission.ACCESS_COARSE_LOCATION
                    ),1000
                )
                return
            }
            locationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                5000,
                0F,
                gpsLocationListener
            )
        }
//------------------------------------------------------//
        if (hasNetwork) {
            if (ActivityCompat.checkSelfPermission(
                    context,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                    context,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.

                ActivityCompat.requestPermissions(
                    context as Activity,
                    arrayOf(
                        android.Manifest.permission.ACCESS_FINE_LOCATION,
                        android.Manifest.permission.ACCESS_COARSE_LOCATION
                    ),1000
                )
                return
            }
            locationManager.requestLocationUpdates(
                LocationManager.NETWORK_PROVIDER,
                5000,
                0F,
                networkLocationListener
            )
        }
        val lastKnownLocationByGps =
            locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
        lastKnownLocationByGps?.let {
            locationByGps.value = lastKnownLocationByGps
        }
//------------------------------------------------------//
        val lastKnownLocationByNetwork =
            locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
        lastKnownLocationByNetwork?.let {
            locationByNetwork.value = lastKnownLocationByNetwork
        }
//------------------------------------------------------//
        if (locationByGps.value != null && locationByNetwork.value != null) {
            if (locationByGps.value!!.accuracy > locationByNetwork.value!!.accuracy) {
                currentLocation.value = locationByGps.value
                Log.d("lpop", "onStart: ${currentLocation.value!!.latitude} >>>>${currentLocation.value!!.longitude}")
                /* latitude = currentLocation.latitude
                 longitude = currentLocation.longitude*/
                // use latitude and longitude as per your need
            } else {
                currentLocation.value = locationByNetwork.value
                Log.d("lpop", "onStart: ${currentLocation.value!!.latitude} >>>>${currentLocation.value!!.longitude}")

                /*latitude = currentLocation.latitude
                longitude = currentLocation.longitude
                */// use latitude and longitude as per your need
            }
        }
    }

    fun setToken(toke: String) {
        token.value = toke
    }

    fun setId(idd: String) {
        id.value = idd
    }
    fun setUserDetailRes(userDetailRes: UserDetailRes) {
        myUserDetailsRes.value = userDetailRes
    }
    fun setMyDeviceId(idd: String) {
        myDeviceId.value = idd
    }

    fun setWaterpoints(list: List<GetWaterPointsResItem>){
        list.forEach {
            if (!waterpoints.contains(it))
                waterpoints.add(it)
        }
    }
    fun setMyWaterpoints(list: List<GetWaterPointsResItem>){
        list.forEach {
            if (!myWaterpoints.contains(it))
                myWaterpoints.add(it)
        }
    }
    fun setTruckDrivers(list: List<GetTruckDriversResItem>){
        list.forEach {
            if (!waterTruckDrivers.contains(it))
                waterTruckDrivers.add(it)
        }
    }
    fun setWatertrucks(list: List<GetTrucksResItem>){
        list.forEach {
            if (!waterTrucks.contains(it))
                waterTrucks.add(it)
        }
        Log.d("mkoi", "setWatertrucks: ${waterTrucks.size}")
    }
    fun setMyWatertrucks(list: List<GetTrucksResItem>){
        list.forEach {
            if (!myWaterTrucks.contains(it))
                myWaterTrucks.add(it)
        }
        Log.d("mkoi", "setWatertrucks: ${waterTrucks.size}")
    }
    fun setMyWatervendors(list: List<GetWaterVendorsResItem>){
        list.forEach {
            if (!myWaterVendors.contains(it))
                myWaterVendors.add(it)
        }
        Log.d("mkoi", "setWatertrucks: ${myWaterVendors.size}")
    }

    fun setShopId(idd: String) {
        shopId.value = idd
    }

    fun setSelectedOrder(paymentReq: PaymentReq) {
        selectedOrder.value = paymentReq
    }

    fun setIndividualOrders(getIndividualOrdersRes: GetIndividualOrdersResItem){
        if (!individualOrders.contains(getIndividualOrdersRes))
            individualOrders.add(getIndividualOrdersRes)
    }

    fun setShopOrder(shopOrdersResponseItem: ShopOrdersResponseItem) {
        if (!shopOrders.contains(shopOrdersResponseItem))
            shopOrders.add(shopOrdersResponseItem)
    }

    fun clearShopOrder() {
        shopOrders.clear()
    }

    fun setSelectedIncomingOrder(idd: ShopOrdersResponseItem) {
        selectedIncomingOrder.value = idd
    }

    fun setCartId(idd: Int) {
        cartId.value = idd
    }

    fun setMyProfile(obj: UserDetailRes) {
        myProfile.value = obj
    }

    fun setIncomingOrderId(idd: Int) {
        incomingOrderId.value = idd
    }

    fun setIncomingShopId(idd: String) {
        incomingShopId.value = idd
    }

    fun setIncomingCustomerId(idd: String) {
        incomingCustomerId.value = idd
    }

    fun setShop(shopsResponseNewItem: ShopsResponseNewItem) {
        shop.value = shopsResponseNewItem
    }

    fun addMyShop(shopsResponseNewItem: ShopsResponseNewItem) {
        myShopsList.add(shopsResponseNewItem)
    }

    fun setCartList(list: MutableList<GetCartResItem>) {
        cartList.clear()
        list.forEach {
            cartList.add(it)
        }
    }
}