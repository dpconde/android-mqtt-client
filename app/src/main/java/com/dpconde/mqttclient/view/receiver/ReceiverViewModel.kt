package com.dpconde.mqttclient.view.receiver

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dpconde.mqttclient.data.objects.ConnectionStatus
import com.dpconde.mqttclient.data.objects.SubscribeStatus
import com.dpconde.mqttclient.mqtt.MqttReceiver
import org.eclipse.paho.client.mqttv3.*

class ReceiverViewModel(private val receiver: MqttReceiver) : ViewModel() {

    val messagesVariable: MutableList<String> = ArrayList()

    val connectionStatus: MutableLiveData<ConnectionStatus> by lazy {
        MutableLiveData<ConnectionStatus>()
    }

    val subscribeStatus: MutableLiveData<SubscribeStatus> by lazy {
        MutableLiveData<SubscribeStatus>()
    }

    val messageToDisplay: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val mqttMessages: MutableLiveData<List<String>> by lazy {
        MutableLiveData<List<String>>()
    }

    init {
        receiver.setCallback(object : MqttCallbackExtended {
            override fun connectComplete(reconnect: Boolean, serverURI: String?) {
                connectionStatus.postValue(ConnectionStatus.CONNECTED)
            }

            override fun messageArrived(topic: String?, message: MqttMessage?) {
                messagesVariable.add(message.toString())
                mqttMessages.postValue(messagesVariable)
            }

            override fun connectionLost(cause: Throwable?) {
                connectionStatus.postValue(ConnectionStatus.DISCONNECTED)
            }

            override fun deliveryComplete(token: IMqttDeliveryToken?) {}
        })
    }

    fun connect() {
        receiver.connect()
    }

    fun disconnect() {
        receiver.disconnect()
    }

    fun subscribe(topic: String) {
        receiver.subscribe(topic, object : IMqttActionListener {
            override fun onSuccess(asyncActionToken: IMqttToken?) {
                subscribeStatus.postValue(SubscribeStatus.SUBSCRIBED)
            }

            override fun onFailure(asyncActionToken: IMqttToken?, exception: Throwable?) {
                subscribeStatus.postValue(SubscribeStatus.UNSUBSCRIBED)
            }
        })
    }
}