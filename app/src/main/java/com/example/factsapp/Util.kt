package com.example.factsapp

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import com.example.factsapp.widget.AppWidget
import java.util.*

object Util {

    fun getMonth(): Int {
        val calendar = Calendar.getInstance()
        calendar.time = Date()
        // Month begin from 0 so you have to add 1
        return calendar.get(Calendar.MONTH) + 1
    }

    fun getDayOfMonth(): Int {
        val calendar = Calendar.getInstance()
        calendar.time = Date()
        return calendar.get(Calendar.DAY_OF_MONTH)
    }

    private fun widgetUpdateIntent(context: Context, loadFromWeb: Boolean): Intent {
        val provider = ComponentName(context, AppWidget::class.java)
        val widgetIds = AppWidgetManager.getInstance(context).getAppWidgetIds(provider)

        val intent = Intent(
            AppWidgetManager.ACTION_APPWIDGET_UPDATE,
            null,
            context,
            AppWidget::class.java
        )

        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, widgetIds)
        intent.putExtra(AppWidget.EXTRA_LOAD_FROM_WEB, loadFromWeb)
        return intent
    }

    fun requestWidgetUpdate(context: Context, loadFromWeb: Boolean) {
        val intent = widgetUpdateIntent(context, loadFromWeb)
        context.sendBroadcast(intent)
    }

    fun requestWidgetUpdatePendingIntent(context: Context, loadFromWeb: Boolean): PendingIntent {
        val intent = widgetUpdateIntent(context, loadFromWeb)
        return PendingIntent.getBroadcast(
            context,
            System.currentTimeMillis().toInt(),
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
    }

}