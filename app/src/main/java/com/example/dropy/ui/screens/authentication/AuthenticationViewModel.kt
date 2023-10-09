package com.example.dropy.ui.screens.authentication

import android.app.Activity
import android.content.Context
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dropy.di.DropyApp
import com.example.dropy.network.models.ForgotPasswordReq
import com.example.dropy.network.models.VerifyDeliveryCodeReq
import com.example.dropy.network.repositories.authentication.AuthenticationRepository
import com.example.dropy.network.use_case.authentication.CheckIfUserExistUseCase
import com.example.dropy.network.use_case.authentication.ForgotPasswordUseCase
import com.example.dropy.network.use_case.authentication.RegisteruserUseCase
import com.example.dropy.network.use_case.getLoginInfo.GetLoginInfoUseCase
import com.example.dropy.network.use_case.saveLoginInfo.SaveLoginInfoUseCase
import com.example.dropy.network.use_case.verifyDeliveryCode.VerifyDeliveryCodeUseCase
import com.example.dropy.ui.app.AppDestinations

import com.example.dropy.ui.app.AppViewModel
import com.example.dropy.ui.app.navigation.AuthenticationDestinations
import com.example.dropy.ui.app.navigation.shopsnavigation.ShopsFrontDestination
import com.example.dropy.ui.components.commons.appdrawer.ProfileTypes
import com.example.dropy.ui.screens.loginAs.LoginAsDialogUiState
import com.example.dropy.ui.screens.loginAs.LoginAsDialogViewModel
import com.example.dropy.ui.screens.nearestWaterPoint.NearestWaterPointUiState
import com.example.dropy.ui.screens.scanQRWater.ScanQRWaterUiState
import com.example.dropy.ui.screens.scanQRWater.ScanQRWaterViewModel
import com.example.dropy.ui.screens.truckIncomingWork.TruckIncomingWorkUiState
import com.example.dropy.ui.screens.truckStartTrip.TruckStartTripViewModel
import com.example.dropy.ui.utils.Resource
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit
import javax.inject.Inject


private object PageErrors {
    const val BLANK_FIRST_NAME_FIELD = "Please enter your first name"
    const val BLANK_LAST_NAME_FIELD = "Please enter your last name"
    const val BLANK_PHONE_NUMBER_FIELD = "Please enter your phone number"
    const val PHONE_NUMBER_TOO_SHORT = "Phone number entered was too short"
    const val BLANK_EMAIL_FIELD = "Please enter your email address"
    const val INVALID_EMAIL_FIELD = "Please enter a valid email address"
    const val INVALID_OTP = "The code you entered is invalid"
    const val BLANK_OTP_FIELD = "Please enter your OTP to continue"
    const val OTP_TOO_SHORT = "Otp entered was too short"
    const val INVALID_REQUEST = "Invalid request parameters check phone number format"
    const val SMA_QUOTA_REACHED = "You have exceeded your limit requests for the day"
    const val USER_ALREADY_EXISTS = "A user with that phone number already exists please login"
    const val UNKNOWN_ERROR = "Unknown error occurred please try again"
}


data class AuthenticationUiState(
    val isLoggedIn: Boolean = false,
    val currentFirstNameValue: String? = null,
    val currentLastNameValue: String? = null,
    val currentEmailValue: String? = null,
    val currentPasswordValue: String? = null,
    val confirmPasswordValue: String? = null,
    val currentOtpValue: String = "",
    val isNewUser: Boolean = false,
    val dialogShow: Boolean = false,
    val currentPhoneNumberValue: String = "07",
    val errorMessages: List<String> = emptyList(),
    val messages: List<String> = emptyList(),
    val isLoading: Boolean = false
)


@HiltViewModel
class AuthenticationViewModel @Inject constructor(
    private val authenticationRepository: AuthenticationRepository,
    private val checkIfUserExistUseCase: CheckIfUserExistUseCase,
    private val registeruserUseCase: RegisteruserUseCase,
    private val saveLoginInfoUseCase: SaveLoginInfoUseCase,
    private val getLoginInfoUseCase: GetLoginInfoUseCase,
    private val forgotPasswordUseCase: ForgotPasswordUseCase,
    private val app: DropyApp,
    private val verifyDeliveryCodeUseCase: VerifyDeliveryCodeUseCase
) : ViewModel() {

    private val uiState = MutableStateFlow(AuthenticationUiState())

    val authenticationUiState: StateFlow<AuthenticationUiState> = uiState.asStateFlow()

    var appViewModel: AppViewModel? = null

    var context: Context? = null

    private val auth = Firebase.auth
    private var authVerificationId: String? = null
    private var resendToken: PhoneAuthProvider.ForceResendingToken? = null

    fun setLoggedInState(state: Boolean) {
        uiState.update { it.copy(isLoggedIn = state) }
    }

    private var callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        override fun onVerificationCompleted(credential: PhoneAuthCredential) {
            signInWithPhoneAuthCredential(credential, activity = context as Activity)
        }

        override fun onVerificationFailed(e: FirebaseException) {
            if (e is FirebaseAuthInvalidCredentialsException) {
                val errorMessages = uiState.value.errorMessages + PageErrors.INVALID_REQUEST
                uiState.update { it.copy(errorMessages = errorMessages) }
            } else if (e is FirebaseTooManyRequestsException) {
                val errorMessages = uiState.value.errorMessages + PageErrors.SMA_QUOTA_REACHED
                uiState.update { it.copy(errorMessages = errorMessages) }
            }
        }

        override fun onCodeSent(
            verificationId: String,
            token: PhoneAuthProvider.ForceResendingToken,
        ) {

            authVerificationId = verificationId
            resendToken = token
            uiState.update {
                it.copy(
                    isLoading = false
                )
            }
            appViewModel?.navigate(AuthenticationDestinations.ENTER_OTP)
        }
    }

    fun verifyDeliveryCode(
        scanQRWaterUiState: ScanQRWaterUiState,
        scanQRWaterViewModel: ScanQRWaterViewModel,
        truckStartTripViewModel: TruckStartTripViewModel,
        nearestWaterPointUiState: NearestWaterPointUiState,
        truckIncomingWorkUiState: TruckIncomingWorkUiState
    ) {
        viewModelScope.launch {
            if (!uiState.value.currentOtpValue.equals("")) {
                uiState.update {
                    it.copy(
                        currentOtpValue = scanQRWaterUiState.code
                    )
                }
                if (uiState.value.currentOtpValue.equals(scanQRWaterUiState.code)) {
                    val item = VerifyDeliveryCodeReq(delivery_code = scanQRWaterUiState.code)

                    verifyDeliveryCodeUseCase(
                        token = "Token " + app.token.value,
                        taskId = scanQRWaterUiState.taskId,
                        verifyDeliveryCodeReq = item
                    ).flowOn(Dispatchers.IO)
                        .catch { e ->
                            // handle exception
                            uiState.update { it.copy(isLoading = false) }
                            scanQRWaterViewModel.navigateOrderComplete(
                                truckStartTripViewModel = truckStartTripViewModel,
                                nearestWaterPointUiState = nearestWaterPointUiState,
                                truckIncomingWorkUiState = truckIncomingWorkUiState
                            )
                        }
                        .collect { result ->
                            // list of users from the network
                            Log.d("uopopi", "getAllShops: $result")
                            when (result) {
                                is Resource.Success -> {

                                    Log.d("KKTAG", "onAddShop: $result")
                                    if (result.data != null) {
                                        //  if (result.data?.resultCode?.equals(0) == true) {
                                        //                                _addShopImagesUiState.update { it.copy(pageLoading = false) }
                                        //                                moveAddProductCategory()
                                        // }

                                        scanQRWaterViewModel.navigateOrderComplete(
                                            truckStartTripViewModel = truckStartTripViewModel,
                                            nearestWaterPointUiState = nearestWaterPointUiState,
                                            truckIncomingWorkUiState = truckIncomingWorkUiState
                                        )
                                        uiState.update {
                                            it.copy(
                                                isLoading = false
                                            )
                                        }
//                                    appViewModel!!.navigate(AppDestinations.WATER_ORDER_SINGLE)

                                    }
                                    //                            _addShopImagesUiState.update { it.copy(pageLoading = false) }


                                }

                                is Resource.Loading -> {
                                    uiState.update { it.copy(isLoading = true) }
                                }

                                is Resource.Error -> {
                                    //                            result.message?.let { message ->
                                    uiState.update {
                                        it.copy(
                                            isLoading = false
                                        )
                                    }
                                    //                            }

                                }
                            }

                        }
                }
            } else {
                Toast.makeText(context, "Code doesn't match", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun onFirstNameChanged(firstName: String) {
        uiState.update { it.copy(currentFirstNameValue = firstName) }
    }

    fun onLastNameChanged(lastName: String) {
        uiState.update { it.copy(currentLastNameValue = lastName) }
    }

    fun onPhoneNumberChanged(phoneNumber: String) {
        uiState.update { it.copy(currentPhoneNumberValue = phoneNumber) }
    }

    fun onEmailChanged(email: String) {
        uiState.update { it.copy(currentEmailValue = email) }
    }

    fun onDialogShow(state: Boolean) {
        uiState.update { it.copy(dialogShow = state) }
    }

    fun onPasswordChanged(email: String) {
        uiState.update { it.copy(currentPasswordValue = email) }
    }

    fun onConfirmPasswordChanged(email: String) {
        uiState.update { it.copy(confirmPasswordValue = email) }
    }

    fun onOtpValueChanged(otp: String) {
        uiState.update { it.copy(currentOtpValue = otp) }
    }

    fun registerNewUser() {
        /*        uiState.update {
                    it.copy(
                        isNewUser = true
                    )
                }
               // initiateOtpVerification(authenticationUiState.value.currentPhoneNumberValue)*/
        if (validateNewUserInput()) {
            if (validatePhoneNumber()) {
                when (userExists()) {
                    true -> {
                        val errorMessages =
                            uiState.value.errorMessages + PageErrors.USER_ALREADY_EXISTS
                        uiState.update { it.copy(errorMessages = errorMessages) }
                        Toast.makeText(context, PageErrors.USER_ALREADY_EXISTS, Toast.LENGTH_SHORT)
                            .show()
                    }

                    false -> {
                        uiState.update { it.copy(isNewUser = true, isLoading = true) }
                        initiateOtpVerification(authenticationUiState.value.currentPhoneNumberValue)

                    }

                    else -> {
                        val errorMessages = uiState.value.errorMessages + PageErrors.UNKNOWN_ERROR
                        uiState.update { it.copy(errorMessages = errorMessages) }
                    }
                }
            }
        }
    }

    private fun userExists(): Boolean {
        val exists: MutableState<Boolean> = mutableStateOf(false)
        viewModelScope.launch {
            uiState.update { it.copy(isLoading = true) }

            checkIfUserExistUseCase.checkIfUserExist(uiState.value.currentPhoneNumberValue).flowOn(
                Dispatchers.IO
            )
                .catch { e ->
                    // handle exception
                }
                .collect { result ->
                    // list of users from the network
                    Log.d("uopopi", "getAllShops: $result")
                    when (result) {
                        is Resource.Success -> {

                            Log.d("huhuh", "onShopSelected: $result")
                            if (result.data != null) {
                                Log.d("TRTRTAG", "userExists: $result")
                                result.data.userExists?.let {
                                    exists.value = it
                                }
                                uiState.update { it.copy(isLoading = true) }
                            }

                        }

                        is Resource.Loading -> {
                            uiState.update { it.copy(isLoading = true) }
                        }

                        is Resource.Error -> {
                            result.message?.let { message ->
                                uiState.update {
                                    it.copy(
                                        isLoading = false,
                                        //errorList = listOf(message)
                                    )
                                }
                            }

                        }
                    }

                }


        }
        return exists.value
    }

    private fun initiateOtpVerification(phoneNumber: String) {
        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(phoneNumber)
            .setTimeout(60L, TimeUnit.SECONDS)
            .setActivity(context as Activity)
            .setCallbacks(callbacks)
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }

    fun onGetOtp() {
        if (validatePhoneNumber()) {
            initiateOtpVerification(authenticationUiState.value.currentPhoneNumberValue)
        }
    }

    fun onResendOtpButtonClicked() {
        val optionsBuilder = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(authenticationUiState.value.currentPhoneNumberValue)
            .setTimeout(60L, TimeUnit.SECONDS)
            .setActivity(context as Activity)
            .setCallbacks(callbacks)
        resendToken?.let { optionsBuilder.setForceResendingToken(it) }
        PhoneAuthProvider.verifyPhoneNumber(optionsBuilder.build())
    }

    fun checkIfCodeMatches(
        scanQRWaterUiState: ScanQRWaterUiState,
        scanQRWaterViewModel: ScanQRWaterViewModel,
        truckStartTripViewModel: TruckStartTripViewModel,
        nearestWaterPointUiState: NearestWaterPointUiState,
        truckIncomingWorkUiState: TruckIncomingWorkUiState
    ) {
        if (uiState.value.currentOtpValue.equals(scanQRWaterUiState.code)) {
//            Toast.makeText(context, "Order verified", Toast.LENGTH_SHORT).show()
            scanQRWaterViewModel.navigateOrderComplete(
                truckStartTripViewModel = truckStartTripViewModel,
                nearestWaterPointUiState = nearestWaterPointUiState,
                truckIncomingWorkUiState = truckIncomingWorkUiState
            )
        } else {
            Toast.makeText(context, "Code doesn't match", Toast.LENGTH_SHORT).show()
        }
    }

    /*fun navigateOrderComplete() {
        if (appViewModel?.appUiState!!.value.activeProfile!!.type.equals(ProfileTypes.CUSTOMER))
            appViewModel?.navigate(ShopsFrontDestination.ORDER_COMPLETE)
        else if (appViewModel?.appUiState!!.value.activeProfile!!.type.equals(ProfileTypes.WATER_TRUCK))
            appViewModel?.navigate(AppDestinations.TRUCK_ORDER_COMPLETE)

    }*/
    fun onVerifyOtp() {
        if (validateOtp()) {

            val credential = PhoneAuthProvider
                .getCredential(authVerificationId!!, authenticationUiState.value.currentOtpValue)
            signInWithPhoneAuthCredential(credential, activity = context as Activity)
        }

    }

    fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential, activity: Activity) {
        auth.signInWithCredential(credential)
            .addOnCompleteListener(activity) { task ->
                if (task.isSuccessful) {
                    val user = task.result?.user
                    if (user != null) {
                        onOtpVerified(firebaseUid = user.uid)
                    }
                } else {
                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        val errorMessages = uiState.value.errorMessages + PageErrors.INVALID_OTP
                        uiState.update { it.copy(errorMessages = errorMessages) }
                    }
                }
            }
    }

    private fun onOtpVerified(firebaseUid: String) {
        Log.d("trtr", "onOtpVerified: ${authenticationUiState.value.isNewUser}")
        if (authenticationUiState.value.isNewUser) {
//            createNewUser(firebaseUid = firebaseUid)
        } else {
            appViewModel?.setFirebaseUid(firebaseUid = firebaseUid)
            appViewModel?.navigate(AppDestinations.APP_HOME)
        }
    }


    fun checkValues(): Boolean {
        val state = mutableStateOf(false)

        if (!authenticationUiState.value.currentPhoneNumberValue.equals("") && !authenticationUiState.value.currentFirstNameValue.equals(
                ""
            ) && !authenticationUiState.value.currentLastNameValue.equals("") && !authenticationUiState.value.currentPasswordValue.equals(
                ""
            ) && !authenticationUiState.value.confirmPasswordValue.equals("")
        ) {

            if (authenticationUiState.value.currentPasswordValue.equals(authenticationUiState.value.confirmPasswordValue))
                state.value = true
            else
                Toast.makeText(context!!, "Passwords dont match", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context!!, "Fill all required fields", Toast.LENGTH_SHORT).show()
        }

        return state.value
    }


    public fun createNewUser(firebaseUid: String, loginAsDialogViewModel: LoginAsDialogViewModel) {
        viewModelScope.launch {
            if (checkValues()) {
                val moved = mutableStateOf(true)
                val phoneNumber = authenticationUiState.value.currentPhoneNumberValue
                val firstName = authenticationUiState.value.currentFirstNameValue
                val lastName = authenticationUiState.value.currentLastNameValue
                val email = authenticationUiState.value.currentEmailValue
                val password = authenticationUiState.value.currentPasswordValue
                // uiState.update { it.copy(isLoading = true) }

                Log.d("TTTAG", "createNewUser: neeew")
                viewModelScope.launch {
                    registeruserUseCase.registerUser(
                        firebase_uid = firebaseUid,
                        first_name = firstName.toString(),
                        last_name = lastName.toString(),
                        phone_number = phoneNumber,
                        email = email.toString(),
                        password1 = password.toString(),
                        password2 = password.toString(),
                        dropy_role = "Truck Driver",
                        context = context!!
                    ).flowOn(Dispatchers.IO)
                              .catch { e ->
                                  // handle exception
                                  apiLogin(loginAsDialogViewModel = loginAsDialogViewModel)

                                  uiState.update { it.copy(isLoading = false) }
                              }
                        .collect { result ->
                            // list of users from the network
                            Log.d("nhpqrt", "getAllShops: ${result.data}")

                            if (result != null)
                                Log.d("koko", "getAllShops: lokiji")
                            when (result) {
                                is Resource.Success -> {

                                    if (result.data != null) {
                                        Log.d("YYYTAG", "createNewUser: $result")
                                        // if (result.data == 0) {
                                        //   appViewModel!!.setFirebaseUid(firebaseUid)
//                                        result.data.id?.let { app.setId(it) }
                                        //createApiToken()
                                        apiLogin(loginAsDialogViewModel = loginAsDialogViewModel)
                                        //  appViewModel?.navigate(AppDestinations.APP_HOME)

                                        uiState.update { it.copy(isLoading = false) }

                                        // }
                                    }


                                }

                                is Resource.Loading -> {
                                    uiState.update { it.copy(isLoading = true) }
                                }

                                is Resource.Error -> {
                                    result.message?.let { message ->
                                        uiState.update {
                                            it.copy(
                                                isLoading = false,
                                                // errorList = listOf(message)
                                            )
                                        }
                                    }

                                }
                            }

                        }


                }
            }
        }
    }

    public fun forgotPassword() {
        val moved = mutableStateOf(true)
        val phoneNumber = authenticationUiState.value.currentPhoneNumberValue
        // uiState.update { it.copy(isLoading = true) }

        val item = ForgotPasswordReq(
            phone_number = phoneNumber,
            otp = "0000"
        )

        Log.d("TTTAG", "createNewUser: neeew")
        viewModelScope.launch {
            forgotPasswordUseCase(
                token = "Token ${app.token.value}",
                forgotPasswordReq = item,
                context = context!!
            ).flowOn(Dispatchers.IO)
                .catch { e ->
                    // handle exception
                }
                .collect { result ->
                    // list of users from the network
                    if (result != null)
                        Log.d("uopopi", "getAllShops: ${result.data}")
                    Log.d("koko", "getAllShops: lokiji")
                    when (result) {
                        is Resource.Success -> {

                            if (result.data != null) {
                                Log.d("YYYTAG", "createNewUser: $result")
                                // if (result.data == 0) {
                                //   appViewModel!!.setFirebaseUid(firebaseUid)
//                                result.data.id?.let { app.setId(it) }
                                //createApiToken()
//                                apiLogin()
                                //  appViewModel?.navigate(AppDestinations.APP_HOME)

                                uiState.update { it.copy(isLoading = false) }

                                // }
                            }


                        }

                        is Resource.Loading -> {
                            uiState.update { it.copy(isLoading = true) }
                        }

                        is Resource.Error -> {
                            result.message?.let { message ->
                                uiState.update {
                                    it.copy(
                                        isLoading = false,
                                        // errorList = listOf(message)
                                    )
                                }
                            }

                        }
                    }

                }


        }
    }

    public fun createApiToken() {
        val moved = mutableStateOf(true)
        val phoneNumber = authenticationUiState.value.currentPhoneNumberValue

        val password = authenticationUiState.value.currentPasswordValue
        uiState.update { it.copy(isLoading = true) }

        Log.d("TTTAG", "createNewUser: neeew")
        viewModelScope.launch {
            registeruserUseCase.createApiToken(
                phone_number = phoneNumber,
                password = password.toString(),
                context = context!!
            ).flowOn(Dispatchers.IO)
                .catch { e ->
                    // handle exception
                }
                .collect { result ->
                    // list of users from the network
                    // Log.d("uopopi", "getAllShops: ${result.data}")
                    when (result) {
                        is Resource.Success -> {

                            if (result.data != null) {
                                Log.d("YYYTAG", "createNewUser: ${result.data}")
                                if (result.data.access != "") {
                                    //   appViewModel!!.setFirebaseUid(firebaseUid)
                                    result.data.access?.let { app.setToken(it) }
                                    appViewModel?.navigate(AppDestinations.APP_HOME)

                                    uiState.update { it.copy(isLoading = false) }

                                }
                            }


                        }

                        is Resource.Loading -> {
                            uiState.update { it.copy(isLoading = true) }
                        }

                        is Resource.Error -> {
                            result.message?.let { message ->
                                uiState.update {
                                    it.copy(
                                        isLoading = false,
                                        // errorList = listOf(message)
                                    )
                                }
                            }

                        }
                    }

                }


        }
    }

    fun checkLoginValues(
        phoneNumbeer: String = "",
        passwordd: String = ""
    ): Boolean {
        val state = mutableStateOf(false)
        if (!phoneNumbeer.equals("") && !passwordd.equals(""))
            state.value = true
        else if (!authenticationUiState.value.currentPhoneNumberValue.equals("") && !authenticationUiState.value.currentPasswordValue.equals(
                ""
            )
        )
            state.value = true
        return state.value
    }

    public fun apiLogin(
        loginAsDialogViewModel: LoginAsDialogViewModel,
        phoneNumbeer: String = "",
        passwordd: String = ""
    ) {
        viewModelScope.launch {
            if (checkLoginValues(phoneNumbeer = phoneNumbeer, passwordd = passwordd)) {
                val moved = mutableStateOf(true)
                val phoneNumber = authenticationUiState.value.currentPhoneNumberValue

                val password = authenticationUiState.value.currentPasswordValue
                uiState.update { it.copy(isLoading = true) }

                Log.d("TTTAG", "createNewUser: $phoneNumbeer   $passwordd")
                viewModelScope.launch {
                    registeruserUseCase.apiLogin(
                        phone_number = if (!phoneNumbeer.equals("")) phoneNumbeer else phoneNumber,
                        password = if (!passwordd.equals("")) passwordd else password.toString(),
                        context = context!!
                    ).flowOn(Dispatchers.IO)/*
                .catch { e ->
                    // handle exception
                }*/
                        .collect { result ->
                            // list of users from the network
                            // Log.d("uopopi", "getAllShops: ${result.data}")
                            when (result) {
                                is Resource.Success -> {
                                    Log.d("hbnoppl", "createNewUser: ${result.data}")

                                    if (result.data != null) {
                                        //   if (result.data.access != "" ) {
                                        //   appViewModel!!.setFirebaseUid(firebaseUid)
                                        result.data.key?.let { app.setToken(it) }
                                        appViewModel!!.getUserDetails()
                                        saveLoginInfo(
                                            phoneNumbeer = phoneNumbeer,
                                            passwordd = passwordd
                                        )
//                                appViewModel?.navigate(AppDestinations.APP_HOME)
                                        loginAsDialogViewModel.changeDialogState(true)

                                        uiState.update { it.copy(isLoading = false) }

                                        //  }
                                    }


                                }

                                is Resource.Loading -> {
                                    uiState.update { it.copy(isLoading = true) }
                                }

                                is Resource.Error -> {
                                    appViewModel!!.navigate(AppDestinations.ON_BOARDING)
                                    uiState.update {
                                        it.copy(
                                            isLoading = false,
                                            // errorList = listOf(message)
                                        )
                                    }


                                }
                            }

                        }


                }
            }
        }
    }

    public fun saveLoginInfo(
        phoneNumbeer: String = "",
        passwordd: String = ""
    ) {
        val moved = mutableStateOf(true)
        val phoneNumber = authenticationUiState.value.currentPhoneNumberValue

        val password = authenticationUiState.value.currentPasswordValue
        uiState.update { it.copy(isLoading = true) }

        Log.d("TTTAG", "createNewUser: neeew")
        viewModelScope.launch {
            saveLoginInfoUseCase(
                phone_number = if (!phoneNumbeer.equals("")) phoneNumbeer else phoneNumber,
                password = if (!passwordd.equals("")) passwordd else password.toString(),
                context = context!!
            ).flowOn(Dispatchers.IO)/*
                .catch { e ->
                    // handle exception
                }*/
                .collect { result ->
                    // list of users from the network
                    // Log.d("uopopi", "getAllShops: ${result.data}")
                    when (result) {
                        is Resource.Success -> {

                            if (result.data != null) {
                                Log.d("tsr", "saved: ${result.data}")
                                //   if (result.data.access != "" ) {
                                //   appViewModel!!.setFirebaseUid(firebaseUid)
                                Toast.makeText(context, "Saved", Toast.LENGTH_SHORT).show()
                                uiState.update { it.copy(isLoading = false) }

                                //  }
                            }


                        }

                        is Resource.Loading -> {
                            uiState.update { it.copy(isLoading = true) }
                        }

                        is Resource.Error -> {
                            result.message?.let { message ->
                                uiState.update {
                                    it.copy(
                                        isLoading = false,
                                        // errorList = listOf(message)
                                    )
                                }
                            }

                        }
                    }

                }


        }
    }

    public fun getLocale(context: Context) {
        app.getCurrentLocation(context)
    }

    public fun getLoginInfo(loginAsDialogViewModel: LoginAsDialogViewModel) {

        Log.d("TTTAG", "createNewUser: neeew")
        viewModelScope.launch {
            getLoginInfoUseCase(
                context = context!!
            ).flowOn(Dispatchers.IO)/*
                .catch { e ->
                    // handle exception
                }*/
                .collect { result ->
                    // list of users from the network
                    // Log.d("uopopi", "getAllShops: ${result.data}")
                    when (result) {
                        is Resource.Success -> {

                            if (result.data != null) {
                                Log.d("tsr", "createNewUser: ${result.data}")
                                //   if (result.data.access != "" ) {
                                //   appViewModel!!.setFirebaseUid(firebaseUid)
                                uiState.update { it.copy(isLoading = false) }
                                if (!result.data.phone_number.equals(""))
                                    apiLogin(
                                        loginAsDialogViewModel,
                                        phoneNumbeer = result.data.phone_number,
                                        passwordd = result.data.password
                                    )
                                else
                                    appViewModel!!.navigate(AppDestinations.ON_BOARDING)

                                //  }
                            }


                        }

                        is Resource.Loading -> {
                            uiState.update { it.copy(isLoading = true) }
                        }

                        is Resource.Error -> {
                            result.message?.let { message ->
                                uiState.update {
                                    it.copy(
                                        isLoading = false,
                                        // errorList = listOf(message)
                                    )
                                }
                            }

                        }
                    }

                }


        }
    }

    fun goToRegister() {
        appViewModel?.navigate(AuthenticationDestinations.REGISTER_PAGE)
    }

    fun goToForgotPssword() {
        appViewModel?.navigate(AuthenticationDestinations.FORGOT_PASSWORD)
    }

    fun goToLogIn() {
        appViewModel?.navigate(AuthenticationDestinations.ENTER_PHONE_NUMBER)
    }

    private fun validateOtp(): Boolean {
        var valid = true
        if (authenticationUiState.value.currentOtpValue.isEmpty()) {
            valid = false
            val errorMessages = uiState.value.errorMessages + PageErrors.BLANK_OTP_FIELD
            uiState.update { it.copy(errorMessages = errorMessages) }
        } else {
            if (authenticationUiState.value.currentOtpValue.length < 4) {
                valid = false
                val errorMessages = uiState.value.errorMessages + PageErrors.OTP_TOO_SHORT
                uiState.update { it.copy(errorMessages = errorMessages) }
            }
        }
        return valid
    }

    private fun validatePhoneNumber(): Boolean {
        var valid = true
        if (authenticationUiState.value.currentPhoneNumberValue === "") {
            valid = false
            val errorMessages = uiState.value.errorMessages + PageErrors.BLANK_PHONE_NUMBER_FIELD
            uiState.update { it.copy(errorMessages = errorMessages) }
        } else {
            if (authenticationUiState.value.currentPhoneNumberValue.length < 10) {
                valid = false
                val errorMessages = uiState.value.errorMessages + PageErrors.PHONE_NUMBER_TOO_SHORT
                uiState.update { it.copy(errorMessages = errorMessages) }
            } else {
                val enteredPhoneNumber = authenticationUiState.value.currentPhoneNumberValue
                val sliced =
                    enteredPhoneNumber.slice(enteredPhoneNumber.length - 9..enteredPhoneNumber.lastIndex)
                val phoneNumber = "+254$sliced"
                uiState.update { it.copy(currentPhoneNumberValue = phoneNumber) }
            }
        }
        return valid
    }

    private fun validateNewUserInput(): Boolean {
        var valid = true

        if (authenticationUiState.value.currentFirstNameValue == "" || authenticationUiState.value.currentFirstNameValue == null) {
            valid = false
            val errorMessages = uiState.value.errorMessages + PageErrors.BLANK_FIRST_NAME_FIELD
            uiState.update { it.copy(errorMessages = errorMessages) }
        }

        if (authenticationUiState.value.currentLastNameValue == "" || authenticationUiState.value.currentLastNameValue == null) {
            valid = false
            val errorMessages = uiState.value.errorMessages + PageErrors.BLANK_LAST_NAME_FIELD
            uiState.update { it.copy(errorMessages = errorMessages) }
        }

        if (authenticationUiState.value.currentEmailValue == "" || authenticationUiState.value.currentEmailValue == null) {
            valid = false
            val errorMessages = uiState.value.errorMessages + PageErrors.BLANK_EMAIL_FIELD
            uiState.update { it.copy(errorMessages = errorMessages) }
        } else {
            val isValidEmail =
                Patterns.EMAIL_ADDRESS.matcher(authenticationUiState.value.currentEmailValue)
                    .matches()
            if (!isValidEmail) {
                valid = false
                val errorMessages = uiState.value.errorMessages + PageErrors.INVALID_EMAIL_FIELD
                uiState.update { it.copy(errorMessages = errorMessages) }
            }
        }
        return valid
    }

    fun onErrorDismissed(errMessage: String) {
        val errMessages = uiState.value.errorMessages.filterNot { it == errMessage }
        uiState.update {
            it.copy(
                errorMessages = errMessages
            )
        }
    }
}