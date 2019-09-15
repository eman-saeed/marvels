package com.example.marvels.network.retrofit

import java.lang.reflect.Type

class ServiceProvider {

    companion object {

        @Synchronized
        fun <T> createService(service: Class<T>): T? {
            return RetrofitObject.getInstance()?.create(service)
        }
    }
}