package com.sirketismi.flights.searchlist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sirketismi.common.Resource
import com.sirketismi.common.flowstate.State
import com.sirketismi.domain.mapper.SearchResponseToUIStateMapper
import com.sirketismi.domain.usecases.SearchListUseCase
import com.sirketismi.entities.model.SearchResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchListViewModel @Inject constructor(
    val searchListUseCase: SearchListUseCase,
    private val mapper: SearchResponseToUIStateMapper) : ViewModel() {
    val state: MutableStateFlow<State?> = MutableStateFlow(null)

    val data = MutableLiveData<SearchResponse?>()

    suspend fun getFlights() {
        searchListUseCase.getList().collectLatest {
            when(it) {
                is Resource.Error -> state.emit(State.error(it.message))
                is Resource.Loading -> state.emit(State.loading())
                is Resource.Success -> {
                    state.emit(State.success())
                    it.data?.data?.let {
                        val mappedData = mapper.map(it)
                        data.postValue(mappedData)
                    }

                }
            }
        }
    }
}

