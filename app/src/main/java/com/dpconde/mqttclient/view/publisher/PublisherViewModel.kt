package com.dpconde.mqttclient.view.publisher

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dpconde.mqttclient.data.objects.ConnectionStatus
import com.dpconde.mqttclient.mqtt.MqttPublisher
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended
import org.eclipse.paho.client.mqttv3.MqttMessage

class PublisherViewModel(private val publisher: MqttPublisher) : ViewModel(){

    val connectionStatus: MutableLiveData<ConnectionStatus> by lazy {
        MutableLiveData<ConnectionStatus>()
    }

    val messageToDisplay: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    init {
        publisher.setCallback(object : MqttCallbackExtended{
            override fun connectComplete(reconnect: Boolean, serverURI: String?) {
                connectionStatus.postValue(ConnectionStatus.CONNECTED)
            }

            override fun messageArrived(topic: String?, message: MqttMessage?) {}

            override fun connectionLost(cause: Throwable?) {
                connectionStatus.postValue(ConnectionStatus.DISCONNECTED)
            }

            override fun deliveryComplete(token: IMqttDeliveryToken?) {
                messageToDisplay.postValue("Message sent properly!")
            }
        })
    }

    fun connect(){
        publisher.connect()
    }

    fun publishMessage(topic: String, message: String){
        publisher.publishMessage(topic, message)
    }

    fun disconnect() {
        publisher.disconnect()
    }
}