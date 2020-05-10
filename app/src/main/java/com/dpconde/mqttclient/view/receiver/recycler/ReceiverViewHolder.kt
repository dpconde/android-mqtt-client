package com.dpconde.mqttclient.view.receiver.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dpconde.mqttclient.R

class ReceiverViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.fragment_receiver_item, parent, false)) {

    private var mTitleView: TextView? = null

    init {
        mTitleView = itemView.findViewById(R.id.textMessage)
    }

    fun bind(message: String) {
        mTitleView?.text = message
    }
}