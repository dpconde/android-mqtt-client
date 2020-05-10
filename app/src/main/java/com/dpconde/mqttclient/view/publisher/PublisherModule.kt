package com.dpconde.mqttclient.view.publisher

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.dpconde.mqttclient.mqtt.MqttPublisher
import com.dpconde.mqttclient.view.di.ViewModelKey
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module(includes = [PublisherModule.ProvideViewModel::class])
abstract class PublisherModule {

    @ContributesAndroidInjector(modules = [InjectViewModel::class])
    abstract fun bind(): PublisherFragment

    @Module
    class ProvideViewModel {

        @Provides
        @IntoMap
        @ViewModelKey(PublisherViewModel::class)
        fun provideListNotesViewModel(publisher: MqttPublisher): ViewModel {
            return PublisherViewModel(publisher)
        }
    }

    @Module
    class InjectViewModel {

        @Provides
        fun provideListNotesViewModel(factory: ViewModelProvider.Factory, target: PublisherFragment): PublisherViewModel{
            return ViewModelProviders.of(target, factory).get(PublisherViewModel::class.java)
        }
    }
}