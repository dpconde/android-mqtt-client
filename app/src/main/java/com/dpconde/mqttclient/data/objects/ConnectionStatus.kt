package com.dpconde.mqttclient.data.objects

enum class ConnectionStatus (val value: String, val color: String){
    CONNECTED("Connected","#8CF097"),
    DISCONNECTED("Disconnected","#FFA08C")
}