package com.dpconde.mqttclient.mqtt

import android.content.Context
import org.eclipse.paho.android.service.MqttAndroidClient
import org.eclipse.paho.client.mqttv3.*
import org.eclipse.paho.client.mqttv3.MqttClient


class MqttClient(private val context: Context, private val url: String?) : MqttPublisher, MqttReceiver{

    private val client by lazy {
        val clientId = MqttClient.generateClientId()
        MqttAndroidClient(context, url, clientId)
    }

    companion object {
        const val TAG = "MqttClient"
    }

    override fun subscribe(topic: String, callback: IMqttActionListener) {
        client.subscribe(topic, 0).actionCallback = callback
    }

    override fun connect(){
        client.connect()
    }

    override fun setCallback(callback : MqttCallbackExtended){
        client.setCallback(callback)
    }

    override fun disconnect() {
        client.disconnect()
    }

    override fun publishMessage(topic: String, msg: String) {

        try {
            val message = MqttMessage()
            message.payload = msg.toByteArray()
            client.publish(topic, message.payload, 0, true)
            //Log.d(TAG, "$msg published to $topic")
        } catch (e: MqttException) {
            //Log.d(TAG, "Error Publishing to $topic: " + e.message)
            e.printStackTrace()
        }
    }
}