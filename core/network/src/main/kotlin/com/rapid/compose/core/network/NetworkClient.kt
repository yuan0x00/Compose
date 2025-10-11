package com.rapid.compose.core.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.security.cert.X509Certificate
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicReference
import javax.net.ssl.SSLContext
import javax.net.ssl.X509TrustManager

object NetworkClient {
    val retrofitRef = AtomicReference<Retrofit?>()

    private var baseUrl = ""
    private var json: Json = JsonConfig.default
    private var connectTimeout: Long = 10
    private var readTimeout: Long = 30
    private var writeTimeout: Long = 30
    private var interceptors: List<Interceptor> = emptyList()

    @JvmStatic
    fun init(
        baseUrl: String,
        json: Json = JsonConfig.default,
        connectTimeout: Long = 10,
        readTimeout: Long = 30,
        writeTimeout: Long = 30,
        interceptors: List<Interceptor> = emptyList()
    ) {
        this.baseUrl = baseUrl
        this.json = json
        this.connectTimeout = connectTimeout
        this.readTimeout = readTimeout
        this.writeTimeout = writeTimeout
        this.interceptors = interceptors
        recreateRetrofit()
    }

    fun recreateRetrofit() {
        val unsafeTrustManager = @Suppress("CustomX509TrustManager")
        object : X509TrustManager {
            override fun checkClientTrusted(chain: Array<out X509Certificate>?, authType: String?) {}
            override fun checkServerTrusted(chain: Array<out X509Certificate>?, authType: String?) {}
            override fun getAcceptedIssuers(): Array<X509Certificate> = arrayOf()
        }

        val sslContext = SSLContext.getInstance("TLS").apply {
            init(null, arrayOf(unsafeTrustManager), null)
        }
        val client = OkHttpClient.Builder()
            .sslSocketFactory(sslContext.socketFactory, unsafeTrustManager)
            .connectTimeout(connectTimeout, TimeUnit.SECONDS)
            .readTimeout(readTimeout, TimeUnit.SECONDS)
            .writeTimeout(writeTimeout, TimeUnit.SECONDS)
            .proxySelector(ProxyManager.proxySelector)
            .apply {
                if (BuildConfig.DEBUG) {
                    addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                }
                interceptors.forEach { addInterceptor(it) }
            }
            .build()

        val contentType = "application/json".toMediaType()
        val converterFactory = json.asConverterFactory(contentType)

        val newRetrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(converterFactory)
            .build()

        retrofitRef.set(newRetrofit)
    }

    inline fun <reified T> create(): T {
        val retrofit = retrofitRef.get()
        check(retrofit != null) { "NetworkClient not initialized!" }
        return retrofit.create(T::class.java)
    }
}
