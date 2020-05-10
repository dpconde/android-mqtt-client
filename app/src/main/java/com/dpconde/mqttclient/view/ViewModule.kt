package com.dpconde.mqttclient.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dpconde.mqttclient.view.di.AppViewModelFactory
import com.dpconde.mqttclient.view.publisher.PublisherModule
import com.dpconde.mqttclient.view.receiver.ReceiverModule
import com.dpconde.mqttclient.view.settings.SettingsModule
import dagger.Module
import dagger.Provides
import javax.inject.Provider

@Module(includes = [PublisherModule::class, ReceiverModule::class, SettingsModule::class])
class ViewModule {

    @Provides
    fun provideViewModelFactory(providers: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>): ViewModelProvider.Factory {
        return AppViewModelFactory(providers)
    }

}