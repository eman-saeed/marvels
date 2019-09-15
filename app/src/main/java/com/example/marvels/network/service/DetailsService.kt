package com.example.marvels.network.service

import com.example.marvels.model.DetailsItem
import com.example.marvels.model.Marvel
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface DetailsService {

    @GET("characters/{characterId}/{serviceName}")
    fun getDetails(
        @Path("characterId") characterId: Int,
        @Path("serviceName") serviceName: String,
        @Query("ts") ts: String,
        @Query("apikey") appId: String,
        @Query("hash") hash: String
    ): Observable<Marvel>
}