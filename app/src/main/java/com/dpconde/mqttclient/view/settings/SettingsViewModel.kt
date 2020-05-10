package com.dpconde.mqttclient.view.settings

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import com.dpconde.mqttclient.R


class SettingsViewModel(private val preferences: SharedPreferences, private val context: Context) :
    ViewModel() {

    fun getCurrentUrl(): String {
        val defaultValue = context.getString(R.string.default_mqtt_url)
        val urlKey = context.getString(R.string.mqtt_url_key)
        return preferences.getString(urlKey, defaultValue)!!
    }

    fun saveUrl(url: String) {
        val editor = preferences.edit()
        editor.putString(context.getString(R.string.mqtt_url_key), url)
        editor.apply()
    }
}