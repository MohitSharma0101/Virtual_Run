package com.mohitsharma.virtualrun.repositories

import com.mohitsharma.virtualrun.db.Run
import com.mohitsharma.virtualrun.db.RunDAO
import javax.inject.Inject

class MainRepository @Inject constructor(
   val runDAO: RunDAO
) {
    suspend fun insertRun(run: Run) = runDAO.insertRun(run)

    suspend fun deleteRun(run: Run) = runDAO.deleteRun(run)

    fun getAllRunByDate() = runDAO.getAllRunByDate()

    fun getAllRunByDistance() = runDAO.getAllRunByDistance()

    fun getAllRunByTime() = runDAO.getAllRunByTime()

    fun getAllRunByAvgSpeed() = runDAO.getAllRunByAvgSpeed()

    fun getAllRunByCalories() = runDAO.getAllRunByCalories()

    fun getTotalDistance() = runDAO.getTotalDistanceInMeters()

    fun getTotalTime() = runDAO.getTotalTimeInMillis()

    fun getTotalCaloriesBurned() = runDAO.getTotalCaloriesBurned()

    fun getAvgOFAvgSpeed() = runDAO.getAvgOfAvgSpeed()

}