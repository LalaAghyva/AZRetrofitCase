package com.sirketismi.flights.searchlist

import androidx.lifecycle.MutableLiveData
import com.sirketismi.common.flowstate.Resource
import com.sirketismi.common.base.BaseViewModel
import com.sirketismi.common.flowstate.State
import com.sirketismi.domain.mapper.SearchResponseToUIStateMapper
import com.sirketismi.domain.usecases.SearchListUseCase
import com.sirketismi.entities.uimodel.FlightSearchUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@HiltViewModel
class SearchListViewModel @Inject constructor(
    val searchListUseCase: SearchListUseCase,
    private val mapper: SearchResponseToUIStateMapper) : BaseViewModel() {

    val data = MutableLiveData<FlightSearchUIState?>()

    suspend fun getFlights() {
        searchListUseCase.getList().collectLatest {
            when(it) {
                is Resource.Error -> state.emit(State.error(it.message))
                is Resource.Loading -> state.emit(State.loading())
                is Resource.Success -> {
                    state.emit(State.success())
                    it.data?.let {
                        data.postValue(it)
                    }

                }
            }
        }
    }
}
