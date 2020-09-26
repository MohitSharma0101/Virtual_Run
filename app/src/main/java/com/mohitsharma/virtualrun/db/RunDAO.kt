package com.mohitsharma.virtualrun.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface RunDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRun(run: Run)

    @Delete
    suspend fun deleteRun(run : Run)

    @Query("SELECT * FROM running_table ORDER BY timeStamp DESC ")
    fun getAllRunByDate(): LiveData<List<Run>>

    @Query("SELECT * FROM running_table ORDER BY timeInMills DESC ")
    fun getAllRunByTime(): LiveData<List<Run>>

    @Query("SELECT * FROM running_table ORDER BY caloriesBurned DESC ")
    fun getAllRunByCalories(): LiveData<List<Run>>

    @Query("SELECT * FROM running_table ORDER BY avgSpeedInKMH DESC ")
    fun getAllRunByAvgSpeed(): LiveData<List<Run>>

    @Query("SELECT * FROM running_table ORDER BY distanceInMeter DESC ")
    fun getAllRunByDistance(): LiveData<List<Run>>

    @Query("SELECT  SUM(timeInMills) FROM running_table")
    fun getTotalTimeInMillis() : LiveData<Long>

    @Query("SELECT  SUM(caloriesBurned) FROM running_table")
    fun getTotalCaloriesBurned() : LiveData<Int>

    @Query("SELECT  SUM(distanceInMeter) FROM running_table")
    fun getTotalDistanceInMeters() : LiveData<Int>

    @Query("SELECT  AVG(avgSpeedInKMH) FROM running_table")
    fun getAvgOfAvgSpeed() : LiveData<Float>




}