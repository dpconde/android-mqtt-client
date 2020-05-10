package com.dpconde.mqttclient.mqtt

import org.eclipse.paho.client.mqttv3.MqttCallbackExtended

interface MqttPublisher {
    fun publishMessage(topic: String, msg: String);
    fun connect()
    fun setCallback(callback : MqttCallbackExtended)
    fun disconnect()
}