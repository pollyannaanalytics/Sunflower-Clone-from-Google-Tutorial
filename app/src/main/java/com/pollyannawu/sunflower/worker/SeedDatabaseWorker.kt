package com.pollyannawu.sunflower.worker

import android.content.Context
import android.util.Log
import com.google.gson.stream.JsonReader
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.pollyannawu.sunflower.data.dataclass.Plant
import com.pollyannawu.sunflower.data.local.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SeedDatabaseWorker(context: Context, workerParams: WorkerParameters) : CoroutineWorker(context, workerParams){
    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
       try {
           val filename = inputData.getString(KEY_FILENAME)
           if (filename != null){
               applicationContext.assets.open(filename).use { inputStream ->
                   JsonReader(inputStream.reader()).use { jsonReader ->
                       val plantType = object : TypeToken<List<Plant>>(){}.type
                       val plantList: List<Plant> = Gson().fromJson(jsonReader, plantType)

                       val database = AppDatabase.getInstance(applicationContext)
                       database.plantDao().upsertAll(plantList)

                       Result.success()
                   }
               }
           }else{
               Log.e(TAG, "Error seeding database - no valid filename")
               Result.failure()
           }
       }catch (e: Exception) {
           Log.e(TAG, "Error seeding database", e)
           Result.failure()
       }
    }

    companion object{
        private const val TAG = "SeedDatabaseWorker"
        const val KEY_FILENAME = "PLANT_DATA_FILENAME"
    }
}