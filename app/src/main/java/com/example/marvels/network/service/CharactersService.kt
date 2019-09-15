package com.example.marvels.network.service

import com.example.marvels.model.Marvel
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query

interface CharactersService {

    @GET("characters")
    fun getCharacters(
        @Query("limit") limit: Int, @Query("offset") offset: Int, @Query("ts") ts: String,
        @Query("apikey") appId: String, @Query("hash") hash: String
    ): Observable<Marvel>
}