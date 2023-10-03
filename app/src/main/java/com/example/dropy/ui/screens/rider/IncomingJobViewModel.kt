package com.example.dropy.ui.screens.rider

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dropy.network.models.acceptjob.request.AcceptJobBody
import com.example.dropy.network.models.customerqr.body.CustomerQrBody
import com.example.dropy.network.models.jobs.infoPojo
import com.example.dropy.network.models.reviewrider.ReviewRiderDataClass
import com.example.dropy.network.models.riderincomingjob.Job
import com.example.dropy.network.models.shopqr.qrbody.QrBody
import com.example.dropy.network.repositories.commons.CommonRepository
import com.example.dropy.network.repositories.rider.RiderRepository
import com.example.dropy.network.use_case.common.DirectionMineUseCase
import com.example.dropy.network.use_case.rider.*
import com.example.dropy.ui.app.AppDestinations
import com.example.dropy.ui.app.AppViewModel
import com.example.dropy.ui.app.RiderDestination
import com.example.dropy.ui.components.rider.incomingjobs.incomingjoblist.IncomingJobListItemDataClass
import com.example.dropy.ui.screens.apphome.AppHomePageViewModel
import com.example.dropy.ui.screens.pin.PinScreenViewModel
import com.example.dropy.ui.screens.shops.backside.shopincomingorders.ShopIncomingOrdersViewModel
import com.example.dropy.ui.screens.shops.frontside.dashboard.orderhistory.CustomerOrderHistoryViewModel
import com.example.dropy.ui.utils.Resource
import com.google.android.gms.maps.model.LatLng
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

data class distancepojo(
    val shopDistance: String = "",
    val shopDistanceNum: Int = 0,
    val shopDistanceduration: Int = 0,
)

data class IncomingJobUiState(
    val jobsList: List<IncomingJobListItemDataClass> = listOf(),
    val polylines: List<LatLng> = listOf(),
    val distanceslist: List<distancepojo> = listOf(),
    val jobs: List<Job>? = listOf(),
    val infolist: List<infoPojo>? = listOf(),
    val estimatedTime: Int = 0,
    val hasongoingJob: Boolean = false,
    val pageLoading: Boolean = false,
    val actionLoading: Boolean = false,
    val errorList: List<String> = emptyList(),
    val messageList: List<String> = emptyList()
)


data class PickCustomerUiState(
    val riderName: String = "RAYMOND KASTEMIL",
    val shopName: String? = null,
    val shopprofilePic: String? = null,
    val section: String = "shop",
    val shopDistance: String = "",
    val shopDistanceNum: Int = 0,
    val qr: String? = null,
    val pageLoading: Boolean = false,
    val actionLoading: Boolean = false,
    val errorList: List<String> = emptyList(),
    val messageList: List<String> = emptyList()
)

@HiltViewModel
class IncomingJobViewModel @Inject constructor(
    private val riderRepository: RiderRepository,
    private val commonRepository: CommonRepository,
    private val directionMineUseCase: DirectionMineUseCase,
    private val reviewRiderUseCase: ReviewRiderUseCase,
    private val getOngoingJobsUseCase: GetOngoingJobsUseCase,
    private val verifyCustQrUseCase: VerifyCustQrUseCase,
    private val verifyQrUseCase: VerifyQrUseCase,
    private  val acceptIncomingJobsuseCase: AcceptIncomingJobsuseCase,
    private val cancelIncomingJobUseCase: CancelIncomingJobUseCase,
    private val getIncomingJobsUseCase: GetIncomingJobsUseCase
) : ViewModel() {
    private val _incomingJobsUiState = MutableStateFlow(IncomingJobUiState())
    val incomingJobsUiState: StateFlow<IncomingJobUiState> =
        _incomingJobsUiState.asStateFlow()
    private val _pickCustomerUiState = MutableStateFlow(PickCustomerUiState())
    val pickCustomerUiState: StateFlow<PickCustomerUiState> =
        _pickCustomerUiState.asStateFlow()

    val list: MutableList<IncomingJobListItemDataClass> = mutableListOf()
    val customername = mutableStateOf("")
    val latLng: MutableState<LatLng?> = mutableStateOf(null)
    fun setCustomerName(text: String) {
        customername.value = text
    }

    fun setShopUrl(text: String) {
        _pickCustomerUiState.update {
            it.copy(
                shopprofilePic = text
            )
        }
    }

    val route: MutableState<String> = mutableStateOf("rider")
    val response: MutableState<QrBody?> = mutableStateOf(null)

    fun verifyQr(
        text: String,
        appViewModel: AppViewModel,
        context: Context,
        pinScreenViewModel: PinScreenViewModel? = null
    ) {

        viewModelScope.launch {
            pinScreenViewModel?.loading(true)
            if (pickCustomerUiState.value.section.equals("shop")) {

                val qrBody = appViewModel.appUiState.value.riderId?.let {
                    QrBody(
                        unique_delivery_id = text,
                        rider_id = it
                    )
                }

                response.value = qrBody
                 qrBody?.let {
                    verifyQrUseCase.verifyQr(it, context).flowOn(Dispatchers.IO)
                        .catch { e ->
                            // handle exception
                        }
                        .collect { result ->
                            // list of users from the network
                            Log.d("uopopi", "getAllShops: $result")
                            when (result) {
                                is Resource.Success -> {
                                    if (result.data != null) {

                                        /*    val qr = dropyRepository.getCustomerQr(6)

                                            if (qr != null) {
                                                _pickCustomerUiState.update { state ->
                                                    state.copy(
                                                        qr = "${qr.encoded_customer_id} , ${qr.encoded_order_number}",
                                                    )
                                                }

                                            }*/
                                        pinScreenViewModel?.loading(false)
                                        setSection("customer")
                                        appViewModel.navigate(RiderDestination.RIDERPICKCUSTOMER)
                                    }
                                    pinScreenViewModel?.loading(false)


                                }
                                is Resource.Loading -> {
                                    pinScreenViewModel?.loading(true)
                                }
                                is Resource.Error -> {
                                    pinScreenViewModel?.loading(false)

                                }
                            }

                        }
                }

            } else {
                val list = text.toMutableList()
                Log.d("jsuh", "verifyQr: $list")

                val first = mutableStateOf(0)
                val second = mutableStateOf(0)
                val third = mutableStateOf(0)
                val fourth = mutableStateOf(0)

                val unique_customer_id = mutableStateOf("")
                val unique_delivery_number = mutableStateOf("")
                list.forEachIndexed { index, item ->

                    if (index in 1..2) {
                        unique_customer_id.value += item.toString()
                    }
                    if (index in 5..12) {
                        unique_delivery_number.value += item.toString()
                    }
                }

                val qrBody = appViewModel.appUiState.value.riderId?.let {
                    CustomerQrBody(
                        unique_customer_id = unique_customer_id.value,
                        unique_delivery_number = unique_delivery_number.value,
                        rider_id = it
                    )
                }

                qrBody?.let {
                    verifyCustQrUseCase.verifyCustQr(it, context).flowOn(Dispatchers.IO)
                        .catch { e ->
                            // handle exception
                        }
                        .collect { result ->
                            // list of users from the network
                            Log.d("uopopi", "getAllShops: $result")
                            when (result) {
                                is Resource.Success -> {

                                    if (result.data != null) {
                                        pinScreenViewModel?.loading(false)
                                        route.value = "customer"
                                        appViewModel.navigate(AppDestinations.REVIEWRIDER)
                                    }
                                    pinScreenViewModel?.loading(false)
                                }
                                is Resource.Loading -> {
                                    pinScreenViewModel?.loading(true)
                                }
                                is Resource.Error -> {
                                    pinScreenViewModel?.loading(false)

                                }
                            }

                        }
                }

            }
        }

    }


    fun resetReviewRoute() {
        route.value = "rider"
    }

    fun setSection(type: String) {
        _pickCustomerUiState.update {
            it.copy(
                section = type
            )
        }
    }

    fun setRiderName(text: String) {
        _pickCustomerUiState.update {
            it.copy(
                riderName = text
            )
        }

    }

    fun setEta(eta: Int) {
        _incomingJobsUiState.update {
            it.copy(
                estimatedTime = eta
            )
        }

    }


    val custom: MutableState<IncomingJobListItemDataClass?> = mutableStateOf(null)

    fun cancelIncomingJob(deliveryPersonId: Int, deliveryId: Int, context: Context) {
        viewModelScope.launch {
             cancelIncomingJobUseCase.cancelIncomingJob(deliveryPersonId, deliveryId).flowOn(Dispatchers.IO)
                .catch { e ->
                    // handle exception
                }
                .collect { result ->
                    // list of users from the network
                    Log.d("uopopi", "getAllShops: $result")
                    when (result) {
                        is Resource.Success -> {

                            if (result.data != null) {
                                _incomingJobsUiState.update {
                                    it.copy(
                                        jobsList = listOf(),
                                    )
                                }
                                Toast.makeText(context, result.data.message, Toast.LENGTH_SHORT).show()
                            }

                        }
                        is Resource.Loading -> {
                            _incomingJobsUiState.update { it.copy(pageLoading = true) }
                        }
                        is Resource.Error -> {
                            result.message?.let { message ->
                                _incomingJobsUiState.update {
                                    it.copy(
                                        pageLoading = false,
                                        errorList = listOf(message)
                                    )
                                }
                            }

                        }
                    }

                }

        }
    }

    fun searchIncomingJob(name: String) {

        viewModelScope.launch {
            incomingJobsUiState.value.jobs?.forEachIndexed { index, it ->
                if (it.check_out?.order?.shop?.shop_name.equals(name)) {
                    fetchMyDirection(it)
                }
            }
        }

    }


    fun setValues(shopName: String, appViewModel: AppViewModel) {
        _pickCustomerUiState.update {
            it.copy(
                riderName = "${appViewModel.appUiState.value.activeProfile?.name}",
                shopName = shopName
            )
        }
        appViewModel.navigate(RiderDestination.RIDERPICKCUSTOMER)
    }

    fun acceptIncomingJob(
        shopName: String,
        appViewModel: AppViewModel,
        deliveryId: Int,
        context: Context
    ) {
        viewModelScope.launch {

            val acceptJobBody =
                AcceptJobBody(delivery_person = appViewModel.appUiState.value.activeProfile!!.id)
             appViewModel.appUiState.value.riderId?.let {
                acceptIncomingJobsuseCase.acceptIncomingJobs(
                    it, deliveryId, acceptJobBody, context
                ).flowOn(Dispatchers.IO)
                    .catch { e ->
                        // handle exception
                    }
                    .collect { result ->
                        // list of users from the network
                        Log.d("uopopi", "getAllShops: $result")
                        when (result) {
                            is Resource.Success -> {

                                Log.d("poiiooih", "acceptIncomingJob: $result")
                                if (result.data != null) {
                                    result.data.forEach { res ->
                                        _pickCustomerUiState.update {
                                            it.copy(
                                                riderName = "${res.delivery_person?.dropyuser?.first_name.toString()} ${res.delivery_person?.dropyuser?.last_name.toString()}",
                                                shopName = shopName
                                            )
                                        }


                                    }

                                    appViewModel.navigate(RiderDestination.RIDERPICKCUSTOMER)

                                }

                            }
                            is Resource.Loading -> {
                                _pickCustomerUiState.update { it.copy(pageLoading = true) }
                            }
                            is Resource.Error -> {
                                result.message?.let { message ->
                                    _pickCustomerUiState.update {
                                        it.copy(
                                            pageLoading = false,
                                            errorList = listOf(message)
                                        )
                                    }
                                }

                            }
                        }

                    }
            }

        }
    }

    fun reviewRider(riderId: Int) {
        viewModelScope.launch {
            val item = ReviewRiderDataClass(
                delivery_person = riderId,
                recent_rating = Random.nextInt(1, 5),
                review_message = "nice rider"
            )

            reviewRiderUseCase.reviewRider(item).flowOn(Dispatchers.IO)
                .catch { e ->
                    // handle exception
                }
                .collect { result ->
                    // list of users from the network
                    Log.d("uopopi", "getAllShops: $result")
                    when (result) {
                        is Resource.Success -> {


                        }
                        is Resource.Loading -> {
                            _incomingJobsUiState.update { it.copy(pageLoading = true) }
                        }
                        is Resource.Error -> {
                            result.message?.let { message ->
                                _incomingJobsUiState.update {
                                    it.copy(
                                        pageLoading = false,
                                        errorList = listOf(message)
                                    )
                                }
                            }

                        }
                    }

                }


        }
    }

    val eta = Random.nextInt(20, 53)
    fun getIncomingJobs(
        riderId: Int,
        appViewModel: AppViewModel,
        appHomePageViewModel: AppHomePageViewModel,
        context: Context
    ) {
        viewModelScope.launch {
            list.clear()

           getIncomingJobsUseCase.getIncomingJobs(riderId, context).flowOn(Dispatchers.IO)
                .catch { e ->
                    // handle exception
                }
                .collect { result ->
                    // list of users from the network
                    Log.d("uopopi", "getAllShops: $result")
                    when (result) {
                        is Resource.Success -> {


                            Log.d("kopokp", "getIncomingJobs: $result")
                            if (result.data != null) {
                                _incomingJobsUiState.update {
                                    it.copy(jobs = result.data.jobs)
                                }

                                result.data.jobs?.forEachIndexed { index, it ->

                                    if (index.equals((result.data.jobs.size - 1))) {
                                        _incomingJobsUiState.update {
                                            it.copy(pageLoading = false)
                                        }
                                    }

                                    custom.value = it.check_out?.order?.shop?.shop_name?.let { it1 ->
                                        IncomingJobListItemDataClass(
                                            customerFirstName = it1,
                                            addressName = it.delivery_location?.place_name,
                                            cost = it.charge?.toInt(),
                                            jobType = "delivery",
                                            timeInMin = eta,
                                            orderId = it.check_out.order.id,
                                            deliveryId = it.id
                                        )


                                    }

                                    val profileurl = mutableStateOf("")

                                    val refCustomValue: MutableState<IncomingJobListItemDataClass?> =
                                        mutableStateOf(custom.value)
                                    appHomePageViewModel.uiState.value.popularShops.forEach { shop ->
                                        if (it.check_out?.order?.shop?.shop_name.equals(shop.shop_name)) {
                                            profileurl.value = shop.shop_logo.toString()
                                            refCustomValue.value =
                                                custom.value?.copy(profilePicUrl = profileurl.value)
                                        }
                                    }

                                    if (!list.contains(refCustomValue.value)) {
                                        refCustomValue.value?.let { it1 -> list.add(it1) }
                                    }


                                    fetchMyDirection(it)

                                    delay(1500)

                                }
                            } else {
                                _incomingJobsUiState.update {
                                    it.copy(pageLoading = false)
                                }
                            }

                        }
                        is Resource.Loading -> {
                            _incomingJobsUiState.update { it.copy(pageLoading = true) }
                        }
                        is Resource.Error -> {
                            result.message?.let { message ->
                                _incomingJobsUiState.update {
                                    it.copy(
                                        pageLoading = false,
                                        errorList = listOf(message)
                                    )
                                }
                            }

                        }
                    }

                }

        }
    }


    fun getOngoingJobs(
        riderId: Int,
        appViewModel: AppViewModel,
        appHomePageViewModel: AppHomePageViewModel,
        context: Context
    ) {
        viewModelScope.launch {
            Log.d("jiijij", "getOngoingJobs: $riderId")
            /*   _incomingJobsUiState.update {
                   it.copy(pageLoading = true)
               }*/
            list.clear()

            getOngoingJobsUseCase.getOngoingJobs(riderId).flowOn(Dispatchers.IO)
                .catch { e ->
                    // handle exception
                }
                .collect { result ->
                    // list of users from the network
                    Log.d("uopopi", "getAllShops: $result")
                    when (result) {
                        is Resource.Success -> {

                            Log.d("kopokp", "getIncomingJobs: $result")
                            if (result.data != null) {
                                _incomingJobsUiState.update {
                                    it.copy(jobs = result.data.jobs, hasongoingJob = true)
                                }

                                result.data.jobs?.forEachIndexed { index, it ->

                                    if (index.equals((result.data.jobs.size - 1))) {
                                        _incomingJobsUiState.update {
                                            it.copy(pageLoading = false)
                                        }
                                    }

                                    custom.value =
                                        it.check_out?.order?.shop?.shop_name?.let { it1 ->
                                            IncomingJobListItemDataClass(
                                                customerFirstName = it1,
                                                addressName = it.delivery_location?.place_name,
                                                cost = it.charge?.toInt(),
                                                jobType = "delivery",
                                                timeInMin = eta,
                                                orderId = it.check_out.order.id,
                                                deliveryId = it.id
                                            )


                                        }

                                    val profileurl = mutableStateOf("")

                                    val refCustomValue: MutableState<IncomingJobListItemDataClass?> =
                                        mutableStateOf(custom.value)
                                    appHomePageViewModel.uiState.value.popularShops.forEachIndexed { index, shop ->
                                        if (it.check_out?.order?.shop?.shop_name.equals(shop.shop_name)) {
                                            profileurl.value = shop.shop_logo.toString()
                                            refCustomValue.value =
                                                custom.value?.copy(profilePicUrl = profileurl.value)
                                        }
                                    }

                                    if (!list.contains(refCustomValue.value)) {
                                        refCustomValue.value?.let { it1 -> list.add(it1) }
                                    }


                                    Log.d("uhu7ytt", "getOngoingJobs: $it")
                                    fetchMyDirection(it)
                                    delay(1500)
                                }
                            } else {
                                getIncomingJobs(
                                    riderId,
                                    appViewModel,
                                    appHomePageViewModel,
                                    context
                                )
                            }

                        }
                        is Resource.Loading -> {
                            _incomingJobsUiState.update { it.copy(pageLoading = true) }
                        }
                        is Resource.Error -> {
                            result.message?.let { message ->
                                _incomingJobsUiState.update {
                                    it.copy(
                                        pageLoading = false,
                                        errorList = listOf(message)
                                    )
                                }
                            }

                        }
                    }

                }

        }
    }

    val currentduration: MutableState<Int> = mutableStateOf(0)
    val infocurrent: MutableList<infoPojo> = mutableListOf()
    suspend fun fetchMyDirection(it: Job, add: Boolean = false) {

        latLng.value =
            it.check_out!!.order!!.shop!!.shop_location!!.latitude?.let { it1 ->
                it.check_out.order!!.shop!!.shop_location!!.longitude?.let { it2 ->
                    LatLng(
                        it1,
                        it2
                    )
                }
            }

        latLng.value?.let { it1 ->
            it.delivery_location?.latitude!!.let { it2 ->
                it.delivery_location.longitude!!.let { it3 -> LatLng(it2, it3) }
            }
                .let { it3 ->
                    directionMineUseCase.directionMine(shopLatLng = it1, myLatLng = it3).flowOn(
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

                                    if (result.data != null) {

                                        val current: MutableList<LatLng> = mutableListOf()
                                        result.data.routes?.forEach {
                                            it.legs?.forEach { leg ->

                                                currentduration.value = leg.duration?.value!!

                                                leg.distance?.text?.let { it1 ->
                                                    leg.distance?.value?.let { it2 ->
                                                        setDistance(
                                                            it1,
                                                            it2,
                                                            currentduration.value
                                                        )
                                                    }
                                                }
                                                leg.steps?.forEach { step ->
                                                    val start =
                                                        step.start_location?.lat?.let { it1 ->
                                                            step.start_location.lng?.let { it2 ->
                                                                LatLng(
                                                                    it1,
                                                                    it2
                                                                )
                                                            }
                                                        }
                                                    val end = step.end_location?.lat?.let { it1 ->
                                                        step.end_location.lng?.let { it2 ->
                                                            LatLng(
                                                                it1,
                                                                it2
                                                            )
                                                        }
                                                    }

                                                    Log.d("kokoko", "start:$start ")
                                                    Log.d("kokoko", "start:$end ")
                                                    if (start != null) {
                                                        current.add(start)
                                                        if (end != null) {
                                                            current.add(end)
                                                        }
                                                    }
                                                }
                                            }

                                        }

                                        _incomingJobsUiState.update {
                                            it.copy(
                                                polylines = current
                                            )
                                        }
                                        _incomingJobsUiState.update {
                                            it.copy(
                                                jobsList = list,
                                                estimatedTime = currentduration.value
                                            )
                                        }

                                        if (add) {
                                            list.forEach {
                                                infocurrent.add(
                                                    infoPojo(
                                                        currentduration.value, price =
                                                        (pickCustomerUiState.value.shopDistanceNum * 50)
                                                    )
                                                )
                                                _incomingJobsUiState.update {
                                                    it.copy(
                                                        infolist = infocurrent
                                                    )
                                                }
                                            }
                                        }

                                        _pickCustomerUiState.update { state ->
                                            state.copy(
                                                shopName = it.check_out?.order?.shop?.shop_name,

                                                )
                                        }
                                    }

                                }
                                is Resource.Loading -> {
                                    _incomingJobsUiState.update { it.copy(pageLoading = true) }
                                    _pickCustomerUiState.update { it.copy(pageLoading = true) }
                                }
                                is Resource.Error -> {
                                    result.message?.let { message ->
                                        _incomingJobsUiState.update {
                                            it.copy(
                                                pageLoading = false,
                                                errorList = listOf(message)
                                            )
                                        }
                                        _pickCustomerUiState.update {
                                            it.copy(
                                                pageLoading = false,
                                                errorList = listOf(message)
                                            )
                                        }

                                    }

                                }
                            }

                        }
                }
        }


    }

    val currentdistance: MutableList<distancepojo> = mutableListOf()
    suspend fun setDistance(num: String, value: Int, duration: Int) {


        val item = distancepojo(
            shopDistance = num,
            shopDistanceNum = value,
            shopDistanceduration = duration
        )
        if (!currentdistance.contains(item)) {
            currentdistance.add(item)
        }

        _incomingJobsUiState.update {
            it.copy(
                distanceslist = currentdistance
            )
        }

        Log.d("lokok", "setDistance: $num  $value")
        _pickCustomerUiState.update {
            it.copy(
                shopDistance = num,
                shopDistanceNum = value
            )
        }
    }

    fun setOrderId(id: Int) {
        val edit = custom.value!!.copy(orderId = id)
        if (list.contains(custom.value)) {
            list.remove(custom.value!!)
            _incomingJobsUiState.update {
                it.copy(
                    jobsList = list
                )
            }
        }
        if (!list.contains(custom.value)) {
            list.add(edit)
            _incomingJobsUiState.update {
                it.copy(
                    jobsList = list
                )
            }
        }

    }

    fun orderAcceptedRider(orderid: Int, shopIncomingOrdersViewModel: ShopIncomingOrdersViewModel) {
        shopIncomingOrdersViewModel.orderAcceptedRider(orderid)
    }

    fun updateIncomingOrdersList(customerOrderHistoryViewModel: CustomerOrderHistoryViewModel) {
        customerOrderHistoryViewModel.updateOrders()
    }
}