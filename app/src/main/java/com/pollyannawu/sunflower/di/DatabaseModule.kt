package com.pollyannawu.sunflower.di

import android.content.Context
import com.pollyannawu.sunflower.data.local.AppDatabase
import com.pollyannawu.sunflower.data.local.GardenPlantingDao
import com.pollyannawu.sunflower.data.local.PlantDao
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext

class DatabaseModule {

    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase{
        return AppDatabase.getInstance(context)
    }


    @Provides
    fun providePlantDao(appDatabase: AppDatabase): PlantDao{
        return appDatabase.plantDao()
    }

    @Provides
    fun provideGardenPlantingDao(appDatabase: AppDatabase): GardenPlantingDao{
        return appDatabase.gardenPlantingDao()
    }
}