package com.example.dropy

import android.Manifest
import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.dropy.ui.app.App
import com.example.dropy.ui.app.AppViewModel
import com.example.dropy.ui.theme.DropyTheme
import dagger.hilt.android.AndroidEntryPoint

import android.app.KeyguardManager
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.hardware.biometrics.BiometricPrompt
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.net.Uri
import android.os.Build
import android.os.CancellationSignal
import android.provider.MediaStore
import android.provider.Settings
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.runtime.*
import androidx.core.app.ActivityCompat
import androidx.core.content.PermissionChecker
import androidx.core.content.PermissionChecker.checkSelfPermission
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.work.WorkManager
import com.example.dropy.ui.app.AppDestinations
import com.example.dropy.ui.components.commons.AddressDataClass
import com.example.dropy.ui.components.commons.maps.selectlocation.SelectLocationViewModel
import com.example.dropy.ui.screens.ImageUri
import com.example.dropy.ui.screens.addWaterPoint.AddWaterpointViewmodel
import com.example.dropy.ui.screens.addWaterTruck.AddWaterTruckViewmodel
import com.example.dropy.ui.screens.addWaterVendor.AddWaterVendorViewModel
import com.example.dropy.ui.screens.rider.IncomingJobViewModel
import com.example.dropy.ui.screens.rider.becomerider.AddRiderViewModel
import com.example.dropy.ui.screens.rider.becomerider.BecomeRiderViewModel
import com.example.dropy.ui.screens.shops.backside.addproduct.AddProductViewModel
import com.example.dropy.ui.screens.shops.backside.addshop.AddShopViewModel
import com.example.dropy.ui.screens.shops.frontside.checkout.CheckoutViewModel
import com.example.dropy.ui.screens.shops.frontside.checkout.trackyourorder.TrackYourOrderViewModel
import com.example.dropy.ui.screens.shops.frontside.dashboard.addresses.singleaddress.SingleAddressViewModel
import com.example.dropy.ui.screens.shops.frontside.dashboard.customerdashboard.CustomerDashboardViewModel
import com.example.dropy.ui.screens.shops.frontside.singleshop.ShopHomePageViewModel
import com.example.dropy.ui.screens.tankerBorehole.TankerBoreholeViewModel
import com.example.dropy.ui.utils.GpsService
import com.example.dropy.ui.utils.LocationService
import com.example.dropy.ui.utils.compressimages.FileUtil
import com.example.dropy.ui.utils.compressimages.UriUtil
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.maps.model.LatLng
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.AutocompleteSessionToken
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.model.TypeFilter
import com.google.android.libraries.places.api.net.*
import com.google.android.libraries.places.widget.Autocomplete
import com.google.android.libraries.places.widget.AutocompleteActivity
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.zxing.integration.android.IntentIntegrator
import id.zelory.compressor.Compressor
import id.zelory.compressor.constraint.format
import id.zelory.compressor.constraint.quality
import id.zelory.compressor.constraint.resolution
import id.zelory.compressor.constraint.size
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.io.File
import java.lang.Math.log10
import java.text.DecimalFormat
import kotlin.math.pow
import kotlin.random.Random


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    // create a CancellationSignal variable and assign a value null to it
    private var cancellationSignal: CancellationSignal? = null

    val addProductViewModel: AddProductViewModel by viewModels()
    val addShopViewModel: AddShopViewModel by viewModels()
    val tankerBoreholeViewModel: TankerBoreholeViewModel by viewModels()
    val addWaterpointViewmodel: AddWaterpointViewmodel by viewModels()
    val addWaterTruckViewmodel: AddWaterTruckViewmodel by viewModels()
    val addWaterVendorViewModel: AddWaterVendorViewModel by viewModels()
    val shopHomePageViewModel: ShopHomePageViewModel by viewModels()
    val addRiderViewModel: AddRiderViewModel by viewModels()
    val singleAddressViewModel: SingleAddressViewModel by viewModels()
    val becomeRiderViewModel: BecomeRiderViewModel by viewModels()
    val appViewModel: AppViewModel by viewModels()
    val incomingJobViewModel: IncomingJobViewModel by viewModels()
    val trackYourOrderViewModel: TrackYourOrderViewModel by viewModels()
    val customerDashboardViewModel: CustomerDashboardViewModel by viewModels()
    private lateinit var mQrResultLauncher: ActivityResultLauncher<Intent>

    val navController: MutableState<NavController?> = mutableStateOf(null)


    var photoSect: String = ""
    var screenSect: String = ""
    val coverbitmap: MutableState<Bitmap?> = mutableStateOf(null)
    var coveruri: MutableState<Uri?> = mutableStateOf(null)
    val multipleBitmap: MutableList<Bitmap> = mutableListOf()
    var multipleUri: MutableList<Uri> = mutableListOf()
    val apiKey = "AIzaSyCnrF8f8v1z1s3NVpPp7Dw-dksvOlQs9XI"
    private val AUTOCOMPLETE_REQUEST_CODE = 1
    val locationsuggest: MutableList<AddressDataClass> = mutableListOf()

    private var actualImage: File? = null
    private var compressedImage: File? = null


    private val obj = LocationService.interfaceImpl()
    private val obj1 = LocationService()

    // Declare the launcher at the top of your Activity/Fragment:
    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            // FCM SDK (and your app) can post notifications.
        } else {
            // TODO: Inform user that that your app will not show notifications.
        }
    }

    private fun askNotificationPermission() {
        // This is only necessary for API level >= 33 (TIRAMISU)
      /*  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) ==
                PackageManager.PERMISSION_GRANTED
            ) {
                // FCM SDK (and your app) can post notifications.
            } else if (shouldShowRequestPermissionRationale(Manifest.permission.POST_NOTIFICATIONS)) {
                // TODO: display an educational UI explaining to the user the features that will be enabled
                //       by them granting the POST_NOTIFICATION permission. This UI should provide the user
                //       "OK" and "No thanks" buttons. If the user selects "OK," directly request the permission.
                //       If the user selects "No thanks," allow the user to continue without notifications.
            } else {
                // Directly ask for the permission
                requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
            }

        }*/
    }

            // create an authenticationCallback
    private val authenticationCallback: BiometricPrompt.AuthenticationCallback
        get() = @RequiresApi(Build.VERSION_CODES.P)
        object : BiometricPrompt.AuthenticationCallback() {
            // here we need to implement two methods
            // onAuthenticationError and onAuthenticationSucceeded
            // If the fingerprint is not recognized by the app it will call
            // onAuthenticationError and show a toast
            override fun onAuthenticationError(errorCode: Int, errString: CharSequence?) {
                super.onAuthenticationError(errorCode, errString)
                notifyUser("Authentication Error : $errString")
            }

            // If the fingerprint is recognized by the app then it will call
            // onAuthenticationSucceeded and show a toast that Authentication has Succeed
            // Here you can also start a new activity after that
            override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult?) {
                super.onAuthenticationSucceeded(result)
                notifyUser("Authentication Succeeded")

                // or start a new Activity

            }
        }

    /*val locationPermissionRequest = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        when {
            permissions.getOrDefault(Manifest.permission.ACCESS_FINE_LOCATION, false) -> {
                // Precise location access granted.
                Toast.makeText(this, "Precise location access granted.", Toast.LENGTH_SHORT).show()
            }
            permissions.getOrDefault(Manifest.permission.ACCESS_COARSE_LOCATION, false) -> {
                // Only approximate location access granted.
                Toast.makeText(
                    this,
                    "Only approximate location access granted.",
                    Toast.LENGTH_SHORT
                ).show()

            }
            else -> {
                // No location access granted.
            }
        }
    }*/

    private fun isLocationPermissionGranted(): Boolean {
        return if (ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {

            false
        } else {
            true
        }
    }



    override fun onStart() {
        super.onStart()
        obj.setRandom(appViewModel)
//        obj.
       /* val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val isGpsEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
        val hasNetwork = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
        //Toast.makeText(this, "$isGpsEnabled", Toast.LENGTH_SHORT).show()
      *//*  if (!isGpsEnabled) {
            val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivity(intent)
        }*//*
        if (isGpsEnabled) {
            if (ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                    this,
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
                    this,
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
                    this,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                    this,
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
                    this,
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
               *//* latitude = currentLocation.latitude
                longitude = currentLocation.longitude*//*
                // use latitude and longitude as per your need
            } else {
                currentLocation.value = locationByNetwork.value
                Log.d("lpop", "onStart: ${currentLocation.value!!.latitude} >>>>${currentLocation.value!!.longitude}")

                *//*latitude = currentLocation.latitude
                longitude = currentLocation.longitude
                *//*// use latitude and longitude as per your need
            }
        }*/
        // Fetching Android ID and storing it into a constant
        val mId = Settings.Secure.getString(contentResolver, Settings.Secure.ANDROID_ID)
        addWaterVendorViewModel.setDeviceId(mId)
        Log.d("ojuh", "onStart:device id $mId")
        // GpsService().turnGPSOn(this)

        /*   lifecycleScope.launch {
               addProductViewModel.addProductCategory().collectLatest {

               }
           }
           lifecycleScope.launch {
               addProductViewModel.deleteProductCategory().collectLatest {

               }
           }*/

    }

    override fun onStop() {
        super.onStop()

        WorkManager.getInstance(this).cancelAllWorkByTag("notification")
    }

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ActivityCompat.requestPermissions(
            this,
            arrayOf(
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION,
            ),
            120
        )

        /* val intent =  Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
       startActivity(intent)*/
        setContent {
            val checkoutViewModel: CheckoutViewModel = hiltViewModel()
            val selectLocationViewModel: SelectLocationViewModel = hiltViewModel()
            val shopuiState by shopHomePageViewModel.shopHomePageUiState.collectAsState()

            LaunchedEffect(key1 = true, block = {

                /*      val service = LocationService()
                      service.setAppViewModel(appViewModel = appViewModel)*/

                Intent(applicationContext, LocationService::class.java).apply {
                    action = LocationService.ACTION_START
                    startService(this)
                }

               // checkoutViewModel.getmethods(shopuiState)
            })

            LaunchedEffect(key1 = true, block = {
                //becomeRiderViewModel.getAlLRiderPools()
                //becomeRiderViewModel.getDeliveryMethods()
            })
            //  checkBiometricSupport()

            DropyTheme {
                App(
                    context = this,
                    checkFingerPrint = {
                        // This creates a dialog of biometric auth and
                        // it requires title , subtitle ,
                        // and description
                        // In our case there is a cancel button by
                        // clicking it, it will cancel the process of
                        // fingerprint authentication
                        /*val biometricPrompt = BiometricPrompt.Builder(this)
                            .setTitle("Signup")
                            .setSubtitle("its good to see you")
                            .setDescription("Uses FP")
                            .setNegativeButton(
                                "Cancel",
                                this.mainExecutor,
                                DialogInterface.OnClickListener { dialog, which ->
                                    notifyUser("Authentication Cancelled")
                                }).build()

                        // start the authenticationCallback in mainExecutor
                        biometricPrompt.authenticate(
                            getCancellationSignal(),
                            mainExecutor,
                            authenticationCallback
                        )*/
                    },
                    choosePhoto = { section, it ->
                        photoSect = it
                        screenSect = section
                        OpenGallery()
                    },
                    addProductViewModel = addProductViewModel,
                    onPlaceNameChanged = {
                        // and once again when the user makes a selection (for example when calling fetchPlace()).
                        // Initialize the SDK


                        // Create a new PlacesClient instance
                        val placesClient = Places.createClient(this)
                        val token = AutocompleteSessionToken.newInstance()

                        /*  // Create a RectangularBounds object.
                          val bounds = RectangularBounds.newInstance(
                              LatLng(-33.880490, 151.184363),
                              LatLng(-33.858754, 151.229596)
                          )*/
                        // Use the builder to create a FindAutocompletePredictionsRequest.
                        val request =
                            FindAutocompletePredictionsRequest.builder()
                                // Call either setLocationBias() OR setLocationRestriction().
                                //     .setLocationBias(bounds)
                                //.setLocationRestriction(bounds)
                                .setOrigin(LatLng(-33.8749937, 151.2041382))
                                .setCountries("KE")
                                .setTypeFilter(TypeFilter.GEOCODE)
                                .setSessionToken(token)
                                .setQuery(it)
                                .build()
                        placesClient.findAutocompletePredictions(request)
                            .addOnSuccessListener { response: FindAutocompletePredictionsResponse ->

                                locationsuggest.clear()
                                for (prediction in response.autocompletePredictions) {
                                    Log.d("jijiyiyt", "onCreate: $prediction")
                                    val address = AddressDataClass(
                                        placeName = prediction.getPrimaryText(null).toString(),
                                        placeId = prediction.placeId,
                                    )
                                    locationsuggest.add(address)

                                    Log.d("huihui", "onCreate: $locationsuggest")
                                    checkoutViewModel.addLocationSuggests(locationsuggest)
                                    Log.i("places", prediction.placeId)
                                    Log.i("places", prediction.getPrimaryText(null).toString())
                                }
                            }.addOnFailureListener { exception: Exception? ->
                                if (exception is ApiException) {
                                    Log.e("places", "Place not found: " + exception.statusCode)
                                }
                            }

                    },
                    openSearchmapdialog = {

                        Places.initialize(applicationContext, apiKey)
                        // Set the fields to specify which types of place data to
                        // return after the user has made a selection.
                        val fields = listOf(
                            Place.Field.ID, Place.Field.NAME, Place.Field.LAT_LNG,
                            Place.Field.ADDRESS,
                            Place.Field.ADDRESS_COMPONENTS
                        )

                        // Start the autocomplete intent.
                        val intent =
                            Autocomplete.IntentBuilder(AutocompleteActivityMode.FULLSCREEN, fields)
                                .build(this)
                        startActivityForResult(intent, AUTOCOMPLETE_REQUEST_CODE)
                    },
                    checkoutViewModel = checkoutViewModel,
                    suggestedLocales = locationsuggest,
                    scanQr = {
                        navController.value = it
                        startScanner()
                    }, selectLocationViewModel = selectLocationViewModel, fetchLocaleDetails = {
                        Log.i(
                            "hiushxsnlks",
                            "Place found: ${it}"
                        )
                        // Define a Place ID.
                        val placeId = it

                        // Initialize the SDK
                        Places.initialize(applicationContext, apiKey)

                        // Create a new PlacesClient instance
                        val placesClient = Places.createClient(this)

// Specify the fields to return.
                        val placeFields = listOf(
                            Place.Field.ID,
                            Place.Field.NAME,
                            Place.Field.LAT_LNG,
                            Place.Field.ADDRESS,
                            Place.Field.ADDRESS_COMPONENTS
                        )

// Construct a request object, passing the place ID and fields array.
                        val request = FetchPlaceRequest.newInstance(placeId, placeFields)


                        placesClient.fetchPlace(request)
                            .addOnSuccessListener { response: FetchPlaceResponse ->
                                val place = response.place
                                Log.i(
                                    "hiushxsnlks",
                                    "Place found: ${place}"
                                )
                                val item = AddressDataClass(
                                    latitude = place.latLng.latitude,
                                    longitude = place.latLng.longitude,
                                    placeName = place.name,
                                    placeId = place.id,
                                    region = place.address,
                                    locationTag = place.addressComponents.asList()[1].shortName
                                )
                                addShopViewModel.addAddress(item)
                                appViewModel.addAddress(item)

                            }.addOnFailureListener { exception: Exception ->
                                if (exception is ApiException) {
                                    Log.e("hiushxsnlks", "Place not found: ${exception.message}")
                                    val statusCode = exception.statusCode
                                    TODO("Handle error with given status code")
                                }
                            }
                    },
                    shopHomePageViewModel = shopHomePageViewModel,
                    singleAddressViewModel = singleAddressViewModel,
                    becomeRiderViewModel = becomeRiderViewModel,
                    incomingJobViewModel = incomingJobViewModel,
                    clearImages = {
                        clearvalues()
                    },
                    customerDashboardViewModel = customerDashboardViewModel
                )
            }
        }

        /*if (ActivityCompat.checkSelfPermission(
                this@MainActivity,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this@MainActivity,
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
            //    return locationres.value

            locationPermissionRequest.launch(
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                )
            )

        }*/

        // Alternative to "onActivityResult", because that is "deprecated"
        mQrResultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                if (it.resultCode == Activity.RESULT_OK) {
                    val result = IntentIntegrator.parseActivityResult(it.resultCode, it.data)

                    if (result.contents != null) {
                        // Do something with the contents (this is usually a URL)
                        println(result.contents)
                        //  quizAnswerViewModel.onAnswerChange(text = result.contents)

                        //navController.value?.navigate(AppDestinations.REVIEWRIDER)
                        incomingJobViewModel.verifyQr(result.contents, appViewModel, this)
                        Toast.makeText(this, result.contents, Toast.LENGTH_SHORT).show()

                    }
                }
            }
    }

    private fun startScanner() {
        val scanner = IntentIntegrator(this)
        // QR Code Format
        scanner.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE)
        // Set Text Prompt at Bottom of QR code Scanner Activity
        scanner.setPrompt("QR Code Scanner Prompt Text")
        // Start Scanner (don't use initiateScan() unless if you want to use OnActivityResult)
        mQrResultLauncher.launch(scanner.createScanIntent())
    }

    fun OpenGallery() {

        if (photoSect == "cover") {
            // val galleryIntent = Intent(/*MediaStore.ACTION_IMAGE_CAPTURE*/)
            val galleryIntent = Intent(Intent.ACTION_PICK)
            //galleryIntent.action = Intent.ACTION_GET_CONTENT
            galleryIntent.type = "image/*"
            startActivityForResult(galleryIntent, 201)
        } else {
            if (Build.VERSION.SDK_INT < 19) {
                var intent = Intent()
                intent.type = "image/*"
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
                intent.action = Intent.ACTION_GET_CONTENT
                startActivityForResult(
                    Intent.createChooser(intent, "Choose Pictures"), 201
                )
            } else { // For latest versions API LEVEL 19+
                var intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
                intent.addCategory(Intent.CATEGORY_OPENABLE)
                intent.type = "image/*"
                startActivityForResult(intent, 201);
            }
        }
    }


    // it will be called when authentication is cancelled by the user
    private fun getCancellationSignal(): CancellationSignal {
        cancellationSignal = CancellationSignal()
        cancellationSignal?.setOnCancelListener {
            notifyUser("Authentication was Cancelled by the user")
        }
        return cancellationSignal as CancellationSignal
    }

    // it checks whether the app the app has fingerprint permission
    @RequiresApi(Build.VERSION_CODES.M)
    private fun checkBiometricSupport(): Boolean {
        val keyguardManager = getSystemService(Context.KEYGUARD_SERVICE) as KeyguardManager
        if (!keyguardManager.isDeviceSecure) {
            notifyUser("Fingerprint authentication has not been enabled in settings")
            return false
        }
        if (checkSelfPermission(
                this,
                Manifest.permission.USE_BIOMETRIC
            ) != PermissionChecker.PERMISSION_GRANTED
        ) {
            notifyUser("Fingerprint Authentication Permission is not enabled")
            return false
        }
        return if (packageManager.hasSystemFeature(PackageManager.FEATURE_FINGERPRINT)) {
            true
        } else true
    }

    // this is a toast method which is responsible for showing toast
    // it takes a string as parameter
    private fun notifyUser(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 201 && resultCode == Activity.RESULT_OK) {
            if (data != null) {

                when {
                    screenSect.equals("product") -> {
                        if (photoSect == "cover") {
                            coveruri.value = data.data!!
                            if (coveruri.value != null) {
                                coverbitmap.value =
                                    MediaStore.Images.Media.getBitmap(
                                        this.contentResolver,
                                        coveruri.value
                                    )
                            }
                            Log.d(
                                "HSUTAG",
                                "onActivityResult: ${coveruri.value} ${coverbitmap.value}"
                            )
                            addProductViewModel.onAddCoverPhoto(
                                coverbitmap.value!!,
                                uri = coveruri.value!!,
                                context = this
                            )
                        } else {

                            // if multiple images are selected
                            if (data?.getClipData() != null) {
                                var count = data.clipData?.itemCount

                                for (i in 0..count!! - 1) {
                                    var uri: Uri = data.clipData?.getItemAt(i)!!.uri
                                    //     iv_image.setImageURI(imageUri) Here you can assign your Image URI to the ImageViews
                                    if (!multipleUri.contains(uri)) {
                                        multipleUri.add(uri)
                                    }
                                    if (uri != null) {
                                        val bitmap =
                                            MediaStore.Images.Media.getBitmap(
                                                this.contentResolver,
                                                uri
                                            )
                                        if (!multipleBitmap.contains(bitmap)) {
                                            multipleBitmap.add(bitmap)
                                            addProductViewModel.onAddMultiplePhoto(
                                                multipleBitmap,
                                                uri = multipleUri,
                                                context = this
                                            )

                                        }
                                    }
                                }

                            } else if (data?.getData() != null) {
                                // if single image is selected

                                var uri: Uri = data.data!!
                                //   iv_image.setImageURI(imageUri) Here you can assign the picked image uri to your imageview

                                if (!multipleUri.contains(uri)) {
                                    multipleUri.add(uri)
                                }
                                if (uri != null) {
                                    val bitmap =
                                        MediaStore.Images.Media.getBitmap(this.contentResolver, uri)
                                    if (!multipleBitmap.contains(bitmap)) {
                                        multipleBitmap.add(bitmap)
                                        addProductViewModel.onAddMultiplePhoto(
                                            multipleBitmap,
                                            uri = multipleUri,
                                            context = this
                                        )
                                    }
                                }
                            }
                            // val uri = data.data!!


                        }
                    }
                    screenSect.equals("rider") -> {
                        if (photoSect == "cover") {
                            coveruri.value = data.data!!
                            if (coveruri.value != null) {
                                coverbitmap.value =
                                    MediaStore.Images.Media.getBitmap(
                                        this.contentResolver,
                                        coveruri.value
                                    )
                            }
                            Log.d(
                                "HSUTAG",
                                "onActivityResult: ${coveruri.value} ${coverbitmap.value}"
                            )
                            addRiderViewModel.onAddCoverPhoto(
                                coverbitmap.value!!,
                                uri = coveruri.value!!,
                                context = this
                            )
                        } else {
                            val uri = data.data!!
                            if (uri != null) {
                                val bitmap =
                                    MediaStore.Images.Media.getBitmap(this.contentResolver, uri)


                                addRiderViewModel.onAddLogo(
                                    bitmap!!,
                                    uri = uri!!,
                                    context = this
                                )
                            }

                        }
                    }
                    screenSect.equals("customerdashboard") -> {
                        if (photoSect == "profile") {
                            //   coveruri.value = data.data!!

                            Log.d(
                                "HSUTAG",
                                "onActivityResult: ${coveruri.value} ${coverbitmap.value}"
                            )
                            customerDashboardViewModel.setValues(
                                uri = data.data!!,
                                bitmap = MediaStore.Images.Media.getBitmap(
                                    this.contentResolver,
                                    data.data!!
                                )
                            )

                        }
                    }
                    screenSect.equals("waterpoint") -> {
                        if (photoSect == "cover") {
                            coveruri.value = data.data!!
                            /*         actualImage = FileUtil.from(this, data.data)*//*?.also {
                                actualImageView.setImageBitmap(loadBitmap(it))
                                actualSizeTextView.text = String.format("Size : %s", getReadableFileSize(it.length()))
                                clearImage()
                            }*/



                            if (coveruri.value != null) {
                                coverbitmap.value =
                                    MediaStore.Images.Media.getBitmap(
                                        this.contentResolver,
                                        coveruri.value
                                    )
                            }
                            Log.d("uu", "onActivityResult: ${coveruri.value} ${coverbitmap.value}")
                            addWaterpointViewmodel.onAddShopCoverPhoto(
                                coverbitmap.value!!,
                                uri = coveruri.value!!,
                                context = this
                            )
                        } else {
                            val uri = data.data!!


                            if (uri != null) {
                                val bitmap =
                                    MediaStore.Images.Media.getBitmap(this.contentResolver, uri)

                                addWaterpointViewmodel.onAddShopLogo(
                                    bitmap!!,
                                    uri = uri!!,
                                    context = this
                                )
                            }

                        }
                    }
                    screenSect.equals("watertruck") -> {
                        if (photoSect == "cover") {
                            coveruri.value = data.data!!
                            /*         actualImage = FileUtil.from(this, data.data)*//*?.also {
                                actualImageView.setImageBitmap(loadBitmap(it))
                                actualSizeTextView.text = String.format("Size : %s", getReadableFileSize(it.length()))
                                clearImage()
                            }*/



                            if (coveruri.value != null) {
                                coverbitmap.value =
                                    MediaStore.Images.Media.getBitmap(
                                        this.contentResolver,
                                        coveruri.value
                                    )
                            }
                            Log.d("uu", "onActivityResult: ${coveruri.value} ${coverbitmap.value}")
                            addWaterTruckViewmodel.onAddShopCoverPhoto(
                                coverbitmap.value!!,
                                uri = coveruri.value!!,
                                context = this
                            )
                        } else {
                            val uri = data.data!!


                            if (uri != null) {
                                val bitmap =
                                    MediaStore.Images.Media.getBitmap(this.contentResolver, uri)

                                addWaterTruckViewmodel.onAddShopLogo(
                                    bitmap!!,
                                    uri = uri!!,
                                    context = this
                                )
                            }

                        }
                    }
                    screenSect.equals("tankerBorehole") -> {
                            coveruri.value = data.data!!
                            /*         actualImage = FileUtil.from(this, data.data)*//*?.also {
                                actualImageView.setImageBitmap(loadBitmap(it))
                                actualSizeTextView.text = String.format("Size : %s", getReadableFileSize(it.length()))
                                clearImage()
                            }*/



                            if (coveruri.value != null) {
                                coverbitmap.value =
                                    MediaStore.Images.Media.getBitmap(
                                        this.contentResolver,
                                        coveruri.value
                                    )
                            }
                            Log.d("uu", "onActivityResult: ${coveruri.value} ${coverbitmap.value}")
                          tankerBoreholeViewModel.setImageCheque(
                                coverbitmap.value!!,
                                uri = coveruri.value!!,
                                context = this
                            )

                    }
                    screenSect.equals("watervendor") -> {
                        if (photoSect == "cover") {
                            coveruri.value = data.data!!
                            /*         actualImage = FileUtil.from(this, data.data)*//*?.also {
                                actualImageView.setImageBitmap(loadBitmap(it))
                                actualSizeTextView.text = String.format("Size : %s", getReadableFileSize(it.length()))
                                clearImage()
                            }*/



                            if (coveruri.value != null) {
                                coverbitmap.value =
                                    MediaStore.Images.Media.getBitmap(
                                        this.contentResolver,
                                        coveruri.value
                                    )
                            }
                            Log.d("uu", "onActivityResult: ${coveruri.value} ${coverbitmap.value}")
                            addWaterVendorViewModel.onAddShopCoverPhoto(
                                coverbitmap.value!!,
                                uri = coveruri.value!!,
                                context = this
                            )
                        } else {
                            val uri = data.data!!


                            if (uri != null) {
                                val bitmap =
                                    MediaStore.Images.Media.getBitmap(this.contentResolver, uri)

                                addWaterVendorViewModel.onAddShopLogo(
                                    bitmap!!,
                                    uri = uri!!,
                                    context = this
                                )
                            }

                        }
                    }
                    else -> {
                        if (photoSect == "cover") {
                            coveruri.value = data.data!!
                            /*         actualImage = FileUtil.from(this, data.data)*//*?.also {
                                actualImageView.setImageBitmap(loadBitmap(it))
                                actualSizeTextView.text = String.format("Size : %s", getReadableFileSize(it.length()))
                                clearImage()
                            }*/



                            if (coveruri.value != null) {
                                coverbitmap.value =
                                    MediaStore.Images.Media.getBitmap(
                                        this.contentResolver,
                                        coveruri.value
                                    )
                            }
                            Log.d("uu", "onActivityResult: ${coveruri.value} ${coverbitmap.value}")
                            addShopViewModel.onAddShopCoverPhoto(
                                coverbitmap.value!!,
                                uri = coveruri.value!!,
                                context = this
                            )
                        } else {
                            val uri = data.data!!


                            if (uri != null) {
                                val bitmap =
                                    MediaStore.Images.Media.getBitmap(this.contentResolver, uri)

                                addShopViewModel.onAddShopLogo(
                                    bitmap!!,
                                    uri = uri!!,
                                    context = this
                                )
                            }

                        }
                    }
                }

            }

        } else if (requestCode == AUTOCOMPLETE_REQUEST_CODE) {
            when (resultCode) {
                Activity.RESULT_OK -> {
                    data?.let {
                        val place = Autocomplete.getPlaceFromIntent(data)
                        val item = AddressDataClass(
                            latitude = place.latLng.latitude,
                            longitude = place.latLng.longitude,
                            placeName = place.name,
                            placeId = place.id,
                            region = place.address,
                            locationTag = place.addressComponents.asList()[1].shortName
                        )
                        addShopViewModel.addAddress(item)
                        tankerBoreholeViewModel.addAddress(item)
                        addWaterpointViewmodel.addAddress(item)
                        addWaterTruckViewmodel.addAddress(item)
                        addWaterVendorViewModel.addAddress(item)
                        appViewModel.addAddress(item)
                        addRiderViewModel.addAddress(item)
                        singleAddressViewModel.addAddress(item)
                        shopHomePageViewModel.getRouteDetails()
                        Log.i("TAGss", "Place: ${place.name}, ${place.id}")
                    }
                }
                AutocompleteActivity.RESULT_ERROR -> {
                    // TODO: Handle the error.
                    data?.let {
                        val status = Autocomplete.getStatusFromIntent(data)
                        Log.i("TAGss", status.statusMessage ?: "")
                    }
                }
                Activity.RESULT_CANCELED -> {
                    // The user canceled the operation.
                }
            }
            return
        }
    }

    private fun showError(errorMessage: String) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
    }


    private fun clearvalues() {
        multipleUri.clear()
        multipleBitmap.clear()
    }
/*    private fun getRandomColor() = Random().run {
        Color.argb(100, nextInt(256), nextInt(256), nextInt(256))
    }*/


    /*  fun getCompressedUri(uri: Uri): Uri {

  // no compression is done here
          //function is converting uri to file and back


          val file = FileUtil.from(this@MainActivity, uri)
        *//*     compressedImage = Compressor.compress(this@MainActivity, file) {
               resolution(1280, 720)
               quality(40)
               format(Bitmap.CompressFormat.WEBP)
               size(2_097_152) // 2 MB
           }*//*
        return FileUtil.getImageUri(this, BitmapFactory.decodeFile(file.absolutePath))

*/
    // }

    /*  private fun compressImage() {
          actualImage?.let { imageFile ->
              lifecycleScope.launch {
                  // Default compression
                  compressedImage = Compressor.compress(this@MainActivity, imageFile)
                  //setCompressedImage()
              }
          } ?: showError("Please choose an image!")
      }*/

/*    private fun customCompressImage() {
        actualImage?.let { imageFile ->
            lifecycleScope.launch {
                // Default compression with custom destination file
                *//*compressedImage = Compressor.compress(this@MainActivity, imageFile) {
                    default()
                    getExternalFilesDir(Environment.DIRECTORY_PICTURES)?.also {
                        val file = File("${it.absolutePath}${File.separator}my_image.${imageFile.extension}")
                        destination(file)
                    }
                }*//*

                // Full custom
                compressedImage = Compressor.compress(this@MainActivity, imageFile) {
                    resolution(1280, 720)
                    quality(40)
                    format(Bitmap.CompressFormat.WEBP)
                    size(2_097_152) // 2 MB
                }
                // setCompressedImage()
            }
        } ?: showError("Please choose an image!")
    }*/

/*    private fun setCompressedImage() {
        compressedImage?.let {
            compressedImageView.setImageBitmap(BitmapFactory.decodeFile(it.absolutePath))
            compressedSizeTextView.text = String.format("Size : %s", getReadableFileSize(it.length()))
            Toast.makeText(this, "Compressed image save in " + it.path, Toast.LENGTH_LONG).show()
            Log.d("Compressor", "Compressed image save in " + it.path)
        }
    }*/

    private fun getReadableFileSize(size: Long): String {
        if (size <= 0) {
            return "0"
        }
        val units = arrayOf("B", "KB", "MB", "GB", "TB")
        val digitGroups = (log10(size.toDouble()) / log10(1024.0)).toInt()
        return DecimalFormat("#,##0.#").format(size / 1024.0.pow(digitGroups.toDouble())) + " " + units[digitGroups]
    }
}