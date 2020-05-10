package com.dpconde.mqttclient

import android.app.Activity
import android.app.Application
import androidx.fragment.app.Fragment
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class App: Application(), HasSupportFragmentInjector {

    @Inject
    lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>

    lateinit var component: AppComponent

    override fun onCreate() {
        super.onCreate()
      /*  if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }*/

    }

    fun init(activity: Activity) {
        component = DaggerAppComponent.builder()
            .application(this)
            .context(this)
            .activity(activity)
            .build()
        component.inject(this)
    }

    override fun supportFragmentInjector() = fragmentInjector
}