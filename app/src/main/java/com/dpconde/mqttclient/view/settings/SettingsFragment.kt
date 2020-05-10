package com.dpconde.mqttclient.view.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.dpconde.mqttclient.R
import com.dpconde.mqttclient.databinding.FragmentSettingsBinding
import com.dpconde.mqttclient.view.di.AppFragment
import javax.inject.Inject

class SettingsFragment : AppFragment() {

    @Inject
    lateinit var viewModel: SettingsViewModel

    private lateinit var binding: FragmentSettingsBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_settings, container, false)
        initComponents();
        return binding.root
    }

    private fun initComponents() {
        binding.editTextURL.setText(viewModel.getCurrentUrl())
        binding.buttonSubscribe.setOnClickListener {
            val url = binding.editTextURL.text.toString()
            viewModel.saveUrl(url)
            Toast.makeText(context, "URL saved", Toast.LENGTH_LONG).show()
        }
    }

}
