package com.sirketismi.domain.usecases

import com.sirketismi.common.Resource
import com.sirketismi.data.repository.FlightRepository
import com.sirketismi.data.repository.FlightRepositoryInterface
import com.sirketismi.domain.mapper.SearchResponseToUIStateMapper
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SearchListUseCase @Inject constructor(
    private val repository: FlightRepositoryInterface,
    private val mapper: SearchResponseToUIStateMapper) {
    suspend fun getList() = flow {
        emit(Resource.Loading())
        repository.getFlights()?.data?.let {
            emit(Resource.Success(mapper.map(it)))
        } ?: emit(Resource.Error("Empty message error"))
    }.catch {exception->
        emit(Resource.Error(exception.localizedMessage))
    }
}
