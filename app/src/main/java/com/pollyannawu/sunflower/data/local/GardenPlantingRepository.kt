package com.pollyannawu.sunflower.data.local

import com.pollyannawu.sunflower.data.dataclass.GardenPlanting
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GardenPlantingRepository @Inject constructor(private val gardenPlantingDao: GardenPlantingDao){
    suspend fun createGardenPlanting(plantId: String){
        val gardenPlanting = GardenPlanting(plantId)
        gardenPlantingDao.insertGardenPlanting(gardenPlanting)
    }


    suspend fun removeGardenPlanting(gardenPlanting: GardenPlanting){
        gardenPlantingDao.deleteGardenPlanting(gardenPlanting)
    }

    fun isPlanted(plantId: String) = gardenPlantingDao.isPlanted(plantId)

    fun getPlantedGardens() = gardenPlantingDao.getPlantedGardens()

    companion object{
        @Volatile
        private var instance: GardenPlantingRepository? = null

        fun getInstance(gardenPlantingDao: GardenPlantingDao){
            instance ?: synchronized(this){
                instance ?: GardenPlantingRepository(gardenPlantingDao = gardenPlantingDao).also { instance = it }
            }
        }
    }
}