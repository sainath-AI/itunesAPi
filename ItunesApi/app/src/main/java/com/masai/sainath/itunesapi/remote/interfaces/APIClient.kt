package com.masai.sainath.itunesapi.remote.interfaces

import com.masai.sainath.itunesapi.remote.responses.ItunesResponseModel
import retrofit2.http.GET
import retrofit2.http.Query

interface APIClient {

    @GET("search")
    suspend fun getResponse(
        @Query("term") term: String
    ): ItunesResponseModel



}