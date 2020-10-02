package com.mohitsharma.virtualrun.service

import android.content.Intent
import androidx.lifecycle.LifecycleService
import com.mohitsharma.virtualrun.others.Constants.ACTION_PAUSE_SERVICE
import com.mohitsharma.virtualrun.others.Constants.ACTION_START_OR_RESUME_SERVICE
import com.mohitsharma.virtualrun.others.Constants.ACTION_STOP_SERVICE
import timber.log.Timber

class TrackingService: LifecycleService() {

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        intent?.let {
            when(it.action){
                ACTION_STOP_SERVICE -> {
                    Timber.d("Stopped Service")
                }
                ACTION_PAUSE_SERVICE -> {
                    Timber.d("Stopped paused")
                }
                ACTION_START_OR_RESUME_SERVICE -> {
                    Timber.d("Service start or resume")
                }
            }
        }

        return super.onStartCommand(intent, flags, startId)
    }
}