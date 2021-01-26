package com.example.mypetproject.data.core

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import com.example.mypetproject.BuildConfig
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import timber.log.Timber
import java.io.IOException
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CoreWebServiceClient @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val okHttpClient: OkHttpClient by lazy { buildOkHttpClient() }

    private fun buildOkHttpClient(): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(makeLoggingInterceptor())
        .addInterceptor(ConnectivityInterceptor(context))
        .addInterceptor(ErrorInterceptor()) //always add this last, so okhttp executes it last
        .connectTimeout(60, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS)
        .build()

    private fun makeLoggingInterceptor(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }
        return logging
    }

    operator fun invoke(): OkHttpClient {
        return okHttpClient
    }

    class ErrorInterceptor : Interceptor {

        override fun intercept(chain: Interceptor.Chain): Response {
            val response = chain.proceed(chain.request())

            Timber.d("Response code ${response.code()}")

            response.headers().toMultimap().forEach {
                Timber.d(it.toString())
            }
            if (response.isSuccessful.not()) { //https://www.restapitutorial.com/httpstatuscodes.html#:~:text=2xx%20Success,received%2C%20understood%2C%20and%20accepted.
                val responseBody = response.header("grpc-message")
                throw ServerErrorException(responseBody?: "There was an error handling your request, please retry.")
            }
            return response
        }

        class ServerErrorException(message: String) : IOException(message)
    }

    class ConnectivityInterceptor(context: Context) : Interceptor {

        private val appContext = context.applicationContext

        override fun intercept(chain: Interceptor.Chain): Response {
            if (!isNetworkConnected(appContext)) throw NoConnectivityException("Please check your connection and retry.")
            return chain.proceed(chain.request())
        }

        private fun isNetworkConnected(context: Context): Boolean {
            val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                cm.getNetworkCapabilities(cm.activeNetwork)
                    ?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            } else {
                cm.activeNetworkInfo?.isConnectedOrConnecting
            } ?: false
        }

        class NoConnectivityException(message: String) : IOException(message)
    }
}
