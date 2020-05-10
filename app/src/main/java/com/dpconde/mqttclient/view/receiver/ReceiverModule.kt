package com.dpconde.mqttclient.view.receiver

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.dpconde.mqttclient.mqtt.MqttReceiver
import com.dpconde.mqttclient.view.di.ViewModelKey
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module(includes = [ReceiverModule.ProvideViewModel::class])
abstract class ReceiverModule {

    @ContributesAndroidInjector(modules = [InjectViewModel::class])
    abstract fun bind(): ReceiverFragment

    @Module
    class ProvideViewModel {

        @Provides
        @IntoMap
        @ViewModelKey(ReceiverViewModel::class)
        fun provideListNotesViewModel(receiver: MqttReceiver): ViewModel {
            return ReceiverViewModel(receiver)
        }
    }

    @Module
    class InjectViewModel {

        @Provides
        fun provideListNotesViewModel(factory: ViewModelProvider.Factory, target: ReceiverFragment): ReceiverViewModel{
            return ViewModelProviders.of(target, factory).get(ReceiverViewModel::class.java)
        }
    }
}