package com.example.factsapp.widget

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import com.example.factsapp.R
import com.example.factsapp.Util
import com.example.factsapp.data.local.SharedPreferencesManager
import com.example.factsapp.data.remote.RetrofitService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AppWidget: AppWidgetProvider() {

    companion object {
        const val EXTRA_LOAD_FROM_WEB = "EXTRA_LOAD_FROM_WEB"
    }

    var loadFromWeb: Boolean = false

    override fun onReceive(context: Context?, intent: Intent?) {
        loadFromWeb = intent?.getBooleanExtra(EXTRA_LOAD_FROM_WEB, false) ?: false
        super.onReceive(context, intent)
    }

    override fun onUpdate(
        context: Context?,
        appWidgetManager: AppWidgetManager?,
        appWidgetIds: IntArray?
    ) {
        super.onUpdate(context, appWidgetManager, appWidgetIds)
        context ?: return
        appWidgetManager ?: return
        appWidgetIds ?: return

        GlobalScope.launch {

            if (loadFromWeb) {
                val fact = RetrofitService.getNumbersApiService().getDateFactSuspended(
                    Util.getMonth(),
                    Util.getDayOfMonth()
                )
                fact?.let {
                    SharedPreferencesManager.saveData(context, fact.text)
                }
            }

            val data = SharedPreferencesManager.loadData(context)
            withContext(Dispatchers.Main) {
                renderWidget(context, appWidgetManager, appWidgetIds, data)
            }

        }


    }



    private fun renderWidget(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray,
        data: String?
    ) {
        val remoteView = RemoteViews(context.packageName, R.layout.app_widget)
        data?.let {
            remoteView.setTextViewText(R.id.refresh_text_view, it)
        }
        remoteView.setOnClickPendingIntent(
            R.id.refresh_button,
            Util.requestWidgetUpdatePendingIntent(context, true)
        )

        appWidgetManager.updateAppWidget(appWidgetIds, remoteView)
    }

}