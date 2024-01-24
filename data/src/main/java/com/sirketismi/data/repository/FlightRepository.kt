package com.sirketismi.data.repository

import com.sirketismi.entities.model.SearchResponse
import com.sirketismi.network.ApiService
import javax.inject.Inject

interface FlightRepositoryInterface {
    suspend fun getFlights() : SearchResponse?
}
class FlightRepository @Inject constructor(private val apiService : ApiService):
    FlightRepositoryInterface {
    override suspend fun getFlights() : SearchResponse? {
        return apiService.getFlights()
}
}