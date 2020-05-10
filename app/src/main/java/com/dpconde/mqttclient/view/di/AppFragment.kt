package com.dpconde.mqttclient.view.di

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import dagger.android.support.AndroidSupportInjection


abstract class AppFragment : Fragment(){

    //private var viewBinding: ViewDataBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidSupportInjection.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //viewBinding = DataBindingUtil.getBinding(view)
    }


}