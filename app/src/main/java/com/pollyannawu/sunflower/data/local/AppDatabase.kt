package com.pollyannawu.sunflower.data.local

import android.content.Context
import androidx.databinding.adapters.Converters
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import com.pollyannawu.sunflower.data.dataclass.GardenPlanting
import com.pollyannawu.sunflower.data.dataclass.Plant
import com.pollyannawu.sunflower.worker.SeedDatabaseWorker
import com.pollyannawu.sunflower.worker.SeedDatabaseWorker.Companion.KEY_FILENAME

@Database(entities = [GardenPlanting::class, Plant::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase(){

    abstract fun gardenPlantingDao(): GardenPlantingDao
    abstract fun plantDao(): PlantDao

    companion object{
        private const val DATABASE_NAME = "sunflower-db"
        private const val PLANT_DATA_FILENAME = "plants.json"

    // for singleton instantiation
    @Volatile
    private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase{
            return instance ?: synchronized(this){
                instance ?: buildDatabase(context).also { instance = it }
            }
        }


        private fun buildDatabase(context: Context): AppDatabase{
            return Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
                .addCallback(
                    object : RoomDatabase.Callback(){
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                        val request = OneTimeWorkRequestBuilder<SeedDatabaseWorker>()
                            .setInputData(workDataOf(KEY_FILENAME to PLANT_DATA_FILENAME))
                            .build()

                            WorkManager.getInstance(context).enqueue(request)
                        }
                    }
                ).build()
        }

    }
}