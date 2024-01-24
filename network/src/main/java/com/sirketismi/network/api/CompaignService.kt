package com.sirketismi.network

import com.sirketismi.entities.model.SearchResponse
import retrofit2.http.GET

interface CompaignService {
    @GET("compaign")
    suspend fun getFlights() : SearchResponse?
}