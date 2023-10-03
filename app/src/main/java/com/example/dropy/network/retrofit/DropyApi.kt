package com.example.dropy.network.retrofit

import android.graphics.Bitmap
import android.net.Uri
import com.example.dropy.network.models.payment.PaymentPojo
import retrofit2.Call
import retrofit2.http.*

interface DropyApi {



    @GET("schedules/")
    fun payStatus(
        @Query("") from: Int,
        @Query("drop_off_point") to: Int,
        @Query("travel_date") date: String,
    ): Call<PaymentPojo>
  /*  //getting all schedules
    @GET("schedules/")
    fun getAllSchedules(
        @Header("Authorization") apikey: String,
        @Header("BMSAndroidVersion") version: Int,
        @Query("pickup_point") from: Int,
        @Query("drop_off_point") to: Int,
        @Query("travel_date") date: String,
    ): Call<Schedule>

    //get specific schedule
    @GET("schedule/")
    fun getTestSchedule(
        @Header("Authorization") apikey: String,
       // @Header("BMSAndroidVersion") version: Int,
        @Query("trip_schedule_id") from: Int,
        @Query("travel_date") date: String,
        @Query("pickup_point") pickupid: Int,
        @Query("drop_off_point") dropoffid: Int,
    ): Call<SeatsTest>

    @GET("schedule/")
    fun getSchedule(
        @Header("Authorization") apikey: String,
       // @Header("BMSAndroidVersion") version: Int,
        @Query("trip_schedule_id") from: Int,
        @Query("travel_date") date: String,
        @Query("pickup_point") pickupid: Int,
        @Query("drop_off_point") dropoffid: Int,
    ): Call<Seats>

    //payment
    @POST("booking/")
    fun confirmpay(
        @Header("Authorization") apikey: String,
        @Body payment: Payment
    ): Call<PaymentRes>

    //trips
    @GET("trips/")
    fun getTrips(
        @Header("Authorization") apikey: String,
        @Header("BMSAndroidVersion") version: Int,
    ): Call<Trips>

    //fleet
    @GET("fleet")
    fun getAllFleets(
        @Header("Authorization") apikey: String,
       // @Header("BMSAndroidVersion") version: Int,
    ): Call<com.buupass.myapplication.data.booking.fleet.Fleet>


    //submit expenses
    @POST("submit-expenses/")
    fun submitexpenses(
        @Header("Authorization") apikey: String,
        @Body submitExpensesReq: SubmitExpensesReq
    ): Call<SubmitexpenseRes>


    //getting all schedules
    @GET("user/bookings/")
    fun getUserBookings(
        @Header("Authorization") apikey: String,
        @Query("booking_date") date: String,
    ): Call<UserBookings>


    //parcel
    //Authenticating user
    @POST("accounts/login/")
    fun signInUser(
        @Body loginRequest: LoginRequest
    ): Call<LoginResponse>

    //get routes
    @GET("routes/")
    fun getRoutes(
        @Header("Authorization") token: String,
        @Header("BMSAndroidVersion") version: Int,
    ): Call<io.andronicus.nestaparcelbooking.data.route.Route>

    //search customer
    @GET("customers/")
    fun getCustomers(
        @Header("Authorization") token: String,
        @Query("findtag") findTag: String,
    ): Call<ExistingUser>

    //search all users
    @GET("users/")
    fun getUsers(
        @Header("Authorization") token: String,
    ): Call<SearchUser>


    //get pay methods
    @GET("payment-types/")
    fun getPaymentMethods(
        @Header("Authorization") token: String
    ): Call<PaymentMethod>

    //booking parcel
    @POST("create/parcel/")
    fun bookParcel(
        @Header("Authorization") token: String,
        @Body createParcelReq: CreateParcelReq
    ): Call<CreateParcelResp>

    //get locations
    @GET("destinations/")
    fun getDestinations(
        @Header("Authorization") token: String,
        @Header("BMSAndroidVersion") version: Int
    ): Call<Locations>

    //get fleet
    @GET("parcel/fleet")
    fun getFleet(
        @Header("Authorization") token: String,
        @Query("start_point") startPoint: String,
        @Query("end_point") endPoint: String
    ): Call<Fleet>

    //get fleet dispatch parcels list
    @GET("parcel/queue/list/")
    fun getDispatchList(
        @Header("Authorization") token: String,
        @Query("start_point") startPoint: String,
        @Query("end_point") endPoint: String,
        @Query("fleet_id") fleetid: String,
    ): Call<ParcelDispatch>

    //get fleet received parcels list
    @GET("parcel/receive/list/")
    fun getReceiveList(
        @Header("Authorization") token: String,
        @Query("start_point") startPoint: String,
        @Query("end_point") endPoint: String,
        @Query("fleet_id") fleetid: String,
    ): Call<ParcelReceive>

    //book Receive list
    @POST("parcel/receive/list/process/")
    fun getReceiveParcels(
        @Header("Authorization") token: String,
        @Body parcelReceiveReq: ParcelReceiveReq
    ): Call<ParcelDisResponse>

    //book dispatch  list
    @POST("parcel/queue/list/process/")
    fun getDispatchParcels(
        @Header("Authorization") token: String,
        @Body parcelDispatchReq: ParcelDispatchReq
    ): Call<ParcelDisResponse>

    //get fleet received parcels list
    @GET("teller/parcel/")
    fun getParcelCollectionList(
        @Header("Authorization") token: String,
        @Query("collection_date") colectionDate: String,
        @Query("agent_id") agentId: String,
        //@Query("collection_number") collectionNumber: String,
    ): Call<ParcelCollection>

    //get user cash list
    @GET("parcel/user/")
    fun getUserCashList(
        @Header("Authorization") token: String,
        @Query("date") Date: String,
    ): Call<UserCollection>

    //get user cod list
    @GET("parcel/cod/user/")
    fun getUserCodList(
        @Header("Authorization") token: String,
        @Query("date") Date: String,
    ): Call<UserCodParcelResp>

    //submit received parcels list
    @POST("teller/parcel/submit/")
    fun submitParcel(
        @Header("Authorization") token: String,
        @Body submitParcel: SubmitParcel
    ): Call<SubmitParcelResp>

    //get manifest routes
    @GET("manifest/routes/")
    fun getManifestRoutes(
        @Header("Authorization") token: String,
        @Header("BMSAndroidVersion") version: Int
    ): Call<ManifestRoute>

    //get manifest passengers
    @GET("manifest/route/passengers/")
    fun getManifestPassengers(
        @Header("Authorization") token: String,
        @Header("BMSAndroidVersion") version: Int,
        @Query("manifest_date") Date: String,
        @Query("trip_schedule") scheduleId: Int,
    ): Call<ManifestPassenger>

    //manifest scaned update
    @POST("manifest/route/passengers/scanned-update/")
    fun manifestScannedUpdate(
        @Header("Authorization") token: String,
        @Body scannedUpReq: ScannedUpReq
    ): Call<ScannedUpdRes>
*/

}