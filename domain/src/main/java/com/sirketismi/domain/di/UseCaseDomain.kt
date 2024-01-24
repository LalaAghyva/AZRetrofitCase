package com.sirketismi.domain.di

import com.sirketismi.data.repository.FlightRepositoryInterface
import com.sirketismi.domain.mapper.SearchResponseToUIStateMapper
import com.sirketismi.domain.usecases.SearchListUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseDomain {
    @Provides
    @Singleton
    fun provideSearchUseCase(repositoryInterface: FlightRepositoryInterface,
                             mapper: SearchResponseToUIStateMapper) = SearchListUseCase(repositoryInterface, mapper)
}