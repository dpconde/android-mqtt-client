package com.dpconde.mqttclient.mqtt

import org.eclipse.paho.client.mqttv3.IMqttActionListener
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended

interface MqttReceiver {

    fun subscribe(topic: String, callback: IMqttActionListener)
    fun connect()
    fun setCallback(callback : MqttCallbackExtended)
    fun disconnect()
}