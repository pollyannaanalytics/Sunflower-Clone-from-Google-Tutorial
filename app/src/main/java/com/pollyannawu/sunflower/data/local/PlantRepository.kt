package com.pollyannawu.sunflower.data.local

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PlantRepository @Inject constructor(private val plantDao: PlantDao) {

    fun getPlants() = plantDao.getPlant()

    fun getPlant(plantId: String) = plantDao.getPlant(plantId)

    fun getPlantsWithGrowZoneNumber(growZoneNumber: Int) = plantDao.getPlantWithGrowZoneNumber(growZoneNumber)

    companion object{
        @Volatile
        private var instance: PlantRepository? = null

        fun getInstance(plantDao: PlantDao) = instance?: synchronized(this){
            instance?: PlantRepository(plantDao).also { instance = it }
        }
    }
}