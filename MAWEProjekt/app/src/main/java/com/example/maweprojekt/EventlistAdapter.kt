package com.example.maweprojekt

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.event.view.*

interface OnEventClickListener {
    fun onEventClicked(event: Event, eventPosition: Int)
}

class EventlistAdapter(
    private val eventList: List<Event>,
    private var eventClickListener: OnEventClickListener
) : RecyclerView.Adapter<EventlistAdapter.ExampleViewHolder>() {

    class ExampleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.image_view
        val textView1: TextView = itemView.tvLine1
        val textView2: TextView = itemView.tvLine2

        fun bind(event: Event, eventPosition: Int, eventClickListener: OnEventClickListener) {
            itemView.setOnClickListener {
                eventClickListener.onEventClicked(event, eventPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExampleViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.event,
            parent, false
        )

        return ExampleViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ExampleViewHolder, position: Int) {
        val currentEvent = eventList[position]

        holder.imageView.setImageResource(currentEvent.imageResource)
        holder.textView1.text = currentEvent.text1
        holder.textView2.text = currentEvent.text2
        holder.bind(currentEvent, position, eventClickListener)
    }

    override fun getItemCount() = eventList.size

}