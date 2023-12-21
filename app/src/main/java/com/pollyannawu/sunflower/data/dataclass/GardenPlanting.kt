package com.pollyannawu.sunflower.data.dataclass

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.Calendar

@Entity(
    tableName = "garden_plantings",
    foreignKeys = [

    ]
)
data class GardenPlanting(
    @ColumnInfo(name = "plant_id") val plantId: String,
    @ColumnInfo(name = "plant_date") val plantDate: Calendar = Calendar.getInstance(),

    @ColumnInfo(name = "last_watering_date")
    val lastWateringDate: Calendar = Calendar.getInstance()
){
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var gardenPlantingId: Long = 0
}
