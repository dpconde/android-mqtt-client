package com.dpconde.mqttclient.data

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataModule {

    @Singleton
    @Provides
    fun providesSharedPreferences(activity: Activity): SharedPreferences {
        return activity.getPreferences(Context.MODE_PRIVATE)
    }
}