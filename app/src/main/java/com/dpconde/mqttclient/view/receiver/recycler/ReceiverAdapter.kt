package com.dpconde.mqttclient.view.receiver.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ReceiverAdapter(var list: MutableList<String>) : RecyclerView.Adapter<ReceiverViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReceiverViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ReceiverViewHolder(
            inflater,
            parent
        )
    }

    override fun onBindViewHolder(holder: ReceiverViewHolder, position: Int) {
        val message: String = list[position]
        holder.bind(message)
    }

    fun updateMessages(messages: List<String>){
        list.clear()
        list.addAll(messages)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = list.size
}