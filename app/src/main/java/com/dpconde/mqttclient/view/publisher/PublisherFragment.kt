package com.dpconde.mqttclient.view.publisher

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.dpconde.mqttclient.R
import com.dpconde.mqttclient.data.objects.ConnectionStatus
import com.dpconde.mqttclient.databinding.FragmentPublisherBinding
import com.dpconde.mqttclient.view.di.AppFragment
import javax.inject.Inject

class PublisherFragment : AppFragment() {

    @Inject
    lateinit var viewModel: PublisherViewModel

    private lateinit var binding: FragmentPublisherBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initObservers()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_publisher, container, false)
        initComponents();
        return binding.root
    }

    private fun initComponents() {
        binding.buttonPublish.setOnClickListener {

            val topic = binding.editTextTopic.text.trim().toString()
            val message = binding.editTextMessage.text.trim().toString()

            when {
                topic.isEmpty() -> binding.editTextTopic.error = ""
                message.isEmpty() -> binding.editTextMessage.error = ""
                else -> viewModel.publishMessage(topic, message)
            }
        }
    }

    private fun initObservers() {
        viewModel.connectionStatus.observe(this, Observer { status ->
            manageConnectionStatus(status)
        })

        viewModel.messageToDisplay.observe(this, Observer { message ->
            displayMessage(message)
        })
    }

    private fun manageConnectionStatus(status: ConnectionStatus){
        when(status) {
            ConnectionStatus.CONNECTED -> {
                displayMessage("Client connected!")
                binding.statusLayout.background
                binding.buttonPublish.isEnabled = true
            }
            ConnectionStatus.DISCONNECTED -> {
                displayMessage("Client disconnected!")
                binding.buttonPublish.isEnabled = false
            }
        }
        binding.statusLayout.setBackgroundColor(Color.parseColor(status.color))
        binding.statusDescription.text = status.name
    }

    private fun displayMessage(message: String){
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.connect()
    }

    override fun onStop() {
        super.onStop()
        viewModel.disconnect()
    }
}
