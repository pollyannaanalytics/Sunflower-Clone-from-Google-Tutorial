package com.pollyannawu.sunflower.data.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.pollyannawu.sunflower.data.dataclass.Plant
import kotlinx.coroutines.flow.Flow

@Dao
interface PlantDao {

    @Query("SELECT * FROM plants ORDER BY name")
    fun getPlant(): Flow<List<Plant>>

    @Query("SELECT * FROM plants WHERE growZoneNumber = :growZoneNumber ORDER BY name")
    fun getPlantWithGrowZoneNumber(growZoneNumber: Int):Flow<List<Plant>>

    @Query("SELECT * FROM plants WHERE id")
    fun getPlant(plantId: String): Flow<Plant>

    @Upsert
    suspend fun upsertAll(plants: List<Plant>)
}