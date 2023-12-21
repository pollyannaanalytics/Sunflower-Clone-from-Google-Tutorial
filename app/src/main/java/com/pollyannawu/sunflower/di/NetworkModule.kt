package com.pollyannawu.sunflower.di

import com.pollyannawu.sunflower.network.UnsplashApiService
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Singleton
    @Provides
    fun provideUnsplashService(): UnsplashApiService{
        return UnsplashApiService.create()
    }
}