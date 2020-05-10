package com.dpconde.mqttclient.view.receiver

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.dpconde.mqttclient.R
import com.dpconde.mqttclient.data.objects.ConnectionStatus
import com.dpconde.mqttclient.data.objects.SubscribeStatus
import com.dpconde.mqttclient.databinding.FragmentReceiverBinding
import com.dpconde.mqttclient.view.di.AppFragment
import com.dpconde.mqttclient.view.receiver.recycler.ReceiverAdapter
import javax.inject.Inject

class ReceiverFragment : AppFragment()  {

    @Inject
    lateinit var viewModel: ReceiverViewModel

    private lateinit var binding: FragmentReceiverBinding

    private lateinit var viewAdapter: ReceiverAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initObservers()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_receiver, container, false)
        initComponents();
        return binding.root
    }

    private fun initComponents() {

        viewAdapter =
            ReceiverAdapter(
                ArrayList()
            )

        binding.messageList.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = viewAdapter
        }

        binding.buttonSubscribe.setOnClickListener {
            val topic = binding.editTextTopic.text.trim().toString()
            if(topic.isEmpty()){
                binding.editTextTopic.error = ""
            }else{
                viewModel.subscribe(topic)
            }
        }
    }

    private fun initObservers() {
        viewModel.connectionStatus.observe(this, Observer { status ->
            manageConnectionStatus(status)
        })

        viewModel.subscribeStatus.observe(this, Observer { status ->
            manageSubscribeStatus(status)
        })

        viewModel.messageToDisplay.observe(this, Observer { message ->
            displayMessage(message)
        })

        viewModel.mqttMessages.observe(this, Observer { mqttMessages ->
            viewAdapter.updateMessages(mqttMessages)
        })
    }

    private fun manageSubscribeStatus(status: SubscribeStatus) {
        when(status) {
            SubscribeStatus.SUBSCRIBED -> {
                displayMessage("Successfully subscribed!")
                binding.buttonSubscribe.isEnabled = false
                binding.editTextTopic.isEnabled = false
            }
            SubscribeStatus.UNSUBSCRIBED -> {
                displayMessage("Error subscribing")
                binding.buttonSubscribe.isEnabled = true
                binding.editTextTopic.isEnabled = true
            }
        }
    }

    private fun manageConnectionStatus(status: ConnectionStatus){
        when(status) {
            ConnectionStatus.CONNECTED -> {
                displayMessage("Client connected!")
                binding.statusLayout.background
                binding.buttonSubscribe.isEnabled = true
                binding.editTextTopic.isEnabled = true
            }
            ConnectionStatus.DISCONNECTED -> {
                displayMessage("Client disconnected!")
                binding.buttonSubscribe.isEnabled = false
                binding.editTextTopic.isEnabled = false
            }
        }
        binding.statusLayout.setBackgroundColor(Color.parseColor(status.color))
        binding.statusDescription.text = status.name
    }

    private fun displayMessage(message: String){
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    override fun onResume() {
        super.onResume()
        viewModel.connect()

    }

    override fun onStop() {
        super.onStop()
        viewModel.disconnect()
    }
}
