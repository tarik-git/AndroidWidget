package com.example.factsapp

import android.app.Application
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.example.factsapp.widget.WidgetUpdateWorker
import java.util.concurrent.TimeUnit

class BaseApp: Application() {

    override fun onCreate() {
        super.onCreate()
        enqueuePeriodicWorker()
    }

    private fun enqueuePeriodicWorker() {
        val constraints = androidx.work.Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()
        val widgetUpdateRequest = PeriodicWorkRequestBuilder<WidgetUpdateWorker>(
            15L,
            TimeUnit.MINUTES
        ).setConstraints(constraints).build()
        WorkManager.getInstance(this).enqueueUniquePeriodicWork(
            "PERIODIC_WORK_REQUEST_UPDATE_WIDGET",
            ExistingPeriodicWorkPolicy.KEEP,
            widgetUpdateRequest
        )
    }

}