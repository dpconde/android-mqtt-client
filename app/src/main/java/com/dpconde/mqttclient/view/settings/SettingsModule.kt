package com.dpconde.mqttclient.view.settings

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.dpconde.mqttclient.view.di.ViewModelKey
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module(includes = [SettingsModule.ProvideViewModel::class])
abstract class SettingsModule {

    @ContributesAndroidInjector(modules = [InjectViewModel::class])
    abstract fun bind(): SettingsFragment

    @Module
    class ProvideViewModel {

        @Provides
        @IntoMap
        @ViewModelKey(SettingsViewModel::class)
        fun provideListNotesViewModel(sharedPreferences: SharedPreferences, context: Context): ViewModel {
            return SettingsViewModel(sharedPreferences, context)
        }
    }

    @Module
    class InjectViewModel {

        @Provides
        fun provideListNotesViewModel(factory: ViewModelProvider.Factory, target: SettingsFragment): SettingsViewModel{
            return ViewModelProviders.of(target, factory).get(SettingsViewModel::class.java)
        }
    }
}