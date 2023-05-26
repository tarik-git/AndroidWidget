package com.example.factsapp.widget

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.factsapp.Util
import com.example.factsapp.data.local.SharedPreferencesManager
import com.example.factsapp.data.remote.RetrofitService

class WidgetUpdateWorker(
    private val context: Context,
    workerParams: WorkerParameters,
) : CoroutineWorker(context, workerParams) {

    companion object {
        private const val TAG = "WidgetUpdateWorker"
    }

    override suspend fun doWork(): Result {
        Log.d(TAG, "doWork")

        val fact = RetrofitService.getNumbersApiService().getDateFactSuspended(
            Util.getMonth(),
            Util.getDayOfMonth()
        )
        fact?.let {
            Log.d(TAG, "new fact data ${it.text}")
            SharedPreferencesManager.saveData(context, it.text)
        }
        Util.requestWidgetUpdate(context, false)
        return Result.success()
    }

}