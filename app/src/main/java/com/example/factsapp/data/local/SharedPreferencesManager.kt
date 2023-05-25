package com.example.factsapp.data.local

import android.content.Context

object SharedPreferencesManager {

    private const val SHARED_PREFERENCES_FACTS = "SHARED_PREFERENCES_FACTS"
    private const val BUNDLE_FACT_TEXT =  "BUNDLE_FACT_TEXT"

    fun saveData(context: Context, data: String?) {
        val sharedPref = context.getSharedPreferences(
            SHARED_PREFERENCES_FACTS,
            Context.MODE_PRIVATE
        )
        sharedPref.edit().putString(BUNDLE_FACT_TEXT, data).apply()
    }

    fun loadData(context: Context): String? {
        val sharedPref = context.getSharedPreferences(
            SHARED_PREFERENCES_FACTS,
            Context.MODE_PRIVATE
        )
        return sharedPref.getString(BUNDLE_FACT_TEXT, null)
    }

}