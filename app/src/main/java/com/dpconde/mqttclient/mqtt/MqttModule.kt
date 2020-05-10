package com.dpconde.mqttclient.mqtt

import android.content.Context
import android.content.SharedPreferences
import com.dpconde.mqttclient.R
import dagger.Module
import dagger.Provides

@Module
class MqttModule{

    @Provides
    fun providesPublisher(context: Context, preferences: SharedPreferences): MqttPublisher {
        val defaultValue = context.getString(R.string.default_mqtt_url)
        val urlKey = context.getString(R.string.mqtt_url_key)
        val baseUrl = preferences.getString(urlKey, defaultValue)
        return MqttClient(context, baseUrl)
    }

    @Provides
    fun providesReceiver(context: Context, preferences: SharedPreferences): MqttReceiver {
        val defaultValue = context.getString(R.string.default_mqtt_url)
        val urlKey = context.getString(R.string.mqtt_url_key)
        val baseUrl = preferences.getString(urlKey, defaultValue)
        return MqttClient(context, baseUrl)
    }
}