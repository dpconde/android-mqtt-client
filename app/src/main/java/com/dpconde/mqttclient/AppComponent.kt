package com.dpconde.mqttclient

import android.app.Activity
import android.content.Context
import com.dpconde.mqttclient.data.DataModule
import com.dpconde.mqttclient.mqtt.MqttModule
import com.dpconde.mqttclient.view.ViewModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Component(modules = [AndroidSupportInjectionModule::class, ViewModule::class, MqttModule::class, DataModule::class])
@Singleton
interface AppComponent {

    fun inject(app: App)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(app: App): Builder

        @BindsInstance
        fun context(context: Context): Builder

        @BindsInstance
        fun activity(context: Activity): Builder

        fun build(): AppComponent
    }
}

