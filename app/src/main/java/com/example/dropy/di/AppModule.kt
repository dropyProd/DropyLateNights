package com.example.dropy.di

//import com.example.dropy.BuildConfig
import android.content.Context
import android.util.Log
import com.example.dropy.BuildConfig
import com.example.dropy.network.services.*
import com.example.dropy.network.services.payment.PaymentService
import com.example.dropy.network.services.users.UsersService
import com.example.dropy.network.services.water.WaterService
import com.example.dropy.ui.components.shops.frontside.singleshop.shophome.BaseUrL
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.*
import io.ktor.client.features.logging.*
import io.ktor.client.features.observer.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.tls.HandshakeCertificates
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import javax.net.ssl.HostnameVerifier
import javax.net.ssl.HttpsURLConnection
import javax.net.ssl.SSLSession

@InstallIn(SingletonComponent::class)
@Module
object AppModule {
    @Singleton
    @Provides
    fun clientCertificates() = HandshakeCertificates.Builder()
        .addPlatformTrustedCertificates()
        .addInsecureHost("api.dropy.co.ke")//ignore untrusted ssl certificate
        .build()


    @Singleton
    @Provides
    fun provideMoshi() = Moshi.Builder().build()

    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor() = HttpLoggingInterceptor().apply {
        setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Singleton
    @Provides
    fun providesOkHttpClient(loggingInterceptor: HttpLoggingInterceptor, clientCertificates: HandshakeCertificates) =
        OkHttpClient.Builder()/*.hostnameVerifier(object : HostnameVerifier {
            override fun verify(hostname: String?, session: SSLSession?): Boolean {
                //return true;
                val hv: HostnameVerifier = HttpsURLConnection.getDefaultHostnameVerifier()
                return hv.verify("api.dropy.co.ke", session)
            }
        } )*/
           .sslSocketFactory(clientCertificates.sslSocketFactory(), clientCertificates.trustManager)//ignore untrusted ssl
            .also {
            if (BuildConfig.DEBUG) {
                it.addInterceptor(loggingInterceptor)
                it.connectTimeout(4, TimeUnit.MINUTES)
                it.readTimeout(4, TimeUnit.MINUTES)

            }
        }.build()

    @Singleton
    @Provides
    fun providesRetrofitInstance(client: OkHttpClient, moshi: Moshi): Retrofit {
        return Retrofit
            .Builder()
            //.baseUrl("https://dropyapi.herokuapp.com/")
            .baseUrl(BaseUrL)
            //.addConverterFactory(MoshiConverterFactory.create(moshi))
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .client(client)
            .build()
    }


    @Singleton
    @Provides
    fun provideApplication(@ApplicationContext app: Context): DropyApp{
        return app as DropyApp
    }

    @Singleton
    @Provides
    fun providesHttpClient(): HttpClient {
        return HttpClient(CIO) {
            /*     install(ContentNegotiation) {
                     json(Json {
                         prettyPrint = true
                         isLenient = true
                         ignoreUnknownKeys = true
                     })
                 }*/
            install(HttpTimeout) {
                requestTimeoutMillis = 25000L
            }
            install(Logging) {
                logger = object : Logger {
                    override fun log(message: String) {
                        Log.i("Logging", message)
                    }
                }
                level = LogLevel.ALL
            }
            install(ResponseObserver) {
                onResponse {}
            }
        }
    }

    @Singleton
    @Provides
    fun providePaymentService(retrofit: Retrofit) = retrofit.create(PaymentService::class.java)


/*
    @Singleton
    @Provides
    fun provideShopFrontService(retrofit: Retrofit, client: OkHttpClient): ShopsFrontService {
        return Retrofit
            .Builder()
            // .baseUrl("https://dropyapi.herokuapp.com/")
            .baseUrl("${BaseUrL}/")
            .addConverterFactory(GsonConverterFactory.create())
            */
/*.addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .client(client)*//*

            .build().create(ShopsFrontService::class.java)
    }

    @Singleton
    @Provides
    fun provideShopBackService(retrofit: Retrofit, client: OkHttpClient): ShopsBackendService {
        return Retrofit
            .Builder()
            // .baseUrl("https://dropyapi.herokuapp.com/")
            .baseUrl("${BaseUrL}/")
            .addConverterFactory(GsonConverterFactory.create())
            */
/*.addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .client(client)*//*

            .build().create(ShopsBackendService::class.java)
    }
*/




    @Singleton
    @Provides
    fun provideDropyUserService(retrofit: Retrofit, client: OkHttpClient): UsersService {
        return Retrofit
            .Builder()
            .baseUrl("${BaseUrL}/dropyusers/")
            .addConverterFactory(GsonConverterFactory.create())
            //.addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .client(client)
            .build().create(UsersService::class.java)
    }
    @Singleton
    @Provides
    fun provideWaterService(retrofit: Retrofit, client: OkHttpClient): WaterService {
        return Retrofit
            .Builder()
            .baseUrl("${BaseUrL}/")
            .addConverterFactory(GsonConverterFactory.create())
            //.addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .client(client)
            .build().create(WaterService::class.java)
    }



    @Singleton
    @Provides
    fun providesAuthenticationApi(
        retrofit: Retrofit,
        client: OkHttpClient
    ): AuthenticationApiService {
        return Retrofit
            .Builder()
            // .baseUrl("https://dropyapi.herokuapp.com/")
            .baseUrl("${BaseUrL}/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build().create(AuthenticationApiService::class.java)
    }

/*
    @Singleton
    @Provides
    fun provideAppViewModel(authenticationApiService: AuthenticationApiService): AppViewModel {
        return AppViewModel(authenticationApiService = authenticationApiService)
    }*/

}