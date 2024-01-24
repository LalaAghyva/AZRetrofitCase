package com.sirketismi.domain.mapper

import com.sirketismi.entities.mapper.BaseMapper
import com.sirketismi.entities.model.Data
import com.sirketismi.entities.model.SearchResponse
import com.sirketismi.entities.uimodel.FlightListUIModel
import com.sirketismi.entities.uimodel.FlightSearchUIState
import com.sirketismi.entities.uimodel.SearchHeaderUiModel
import javax.inject.Inject

class SearchResponseToUIStateMapper @Inject constructor(): BaseMapper<Data, FlightSearchUIState> {
    override fun map(input: Data): FlightSearchUIState {
        return FlightSearchUIState(
            header = input.getSearchHeader(),
            flights = input.getFlights()
        )
    }

    private fun Data.getSearchHeader() : SearchHeaderUiModel {
        return SearchHeaderUiModel(
            origin = search_parameters?.origin?.city_name ?: "",
            destination = search_parameters?.destination?.city_name ?: "")
    }

    private fun Data.getFlights() : List<FlightListUIModel> {
        return flights?.departure?.map {
            FlightListUIModel(
                airlineName = it.segments?.firstOrNull()?.marketing_airline ?: "",
                airlineIcon = "",
                price = 0.0)
        } ?: listOf()
    }
}