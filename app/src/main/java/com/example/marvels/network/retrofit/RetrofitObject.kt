package com.example.marvels.network.retrofit

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.math.BigInteger
import java.security.MessageDigest
import java.security.Timestamp
import java.time.Instant


class RetrofitObject private constructor() {

    companion object {

        private val TIMESTAMP = "1"
        private val API_KEY: String = "e628c9a6e288c7ac881b96c4cb74e008"
        private val PRIVATE_API_KEY: String = "ea001fa916295db27c20bcefee0be09da640a1c9"

        private val BASE_URL: String = "https://gateway.marvel.com:443/v1/public/"

        private var ourInstance: Retrofit? = null

        @Synchronized
        fun getInstance(): Retrofit? {

            if (ourInstance == null) {
                ourInstance = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()
            }
            return ourInstance
        }

        fun getAPIKey(): String {
            return API_KEY;
        }

        fun getTimeStamp(): String {
            return TIMESTAMP
        }

        fun getHashed(): String {
            val messageDigest = MessageDigest.getInstance("MD5")
            val message = TIMESTAMP + PRIVATE_API_KEY + API_KEY
            return BigInteger(1, messageDigest.digest(message.toByteArray())).toString(16).padStart(32, '0')
        }
    }
}