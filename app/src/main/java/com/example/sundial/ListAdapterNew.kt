package com.example.sundial

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ListAdapterNew(private val timesheetlist : ArrayList<NewTimesheetClass>) : RecyclerView.Adapter<ListAdapterNew.ListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item,parent,false)
        return ListViewHolder(itemView)

    }

    override fun getItemCount(): Int {
        return timesheetlist.size
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val currentItem = timesheetlist[position]

        holder.category.text = "Category: " + currentItem.category
        holder.description.text = "Description: " + currentItem.description
        holder.date.text = "Date: " + currentItem.date
        holder.startTime.text = "Time Started: " + currentItem.startTime
        holder.endTime.text = "Time Finished: " + currentItem.endTime
    }

    class ListViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

        val category : TextView = itemView.findViewById(R.id.txtRowCategory)
        val description : TextView = itemView.findViewById(R.id.txtRowDescription)
        val date : TextView = itemView.findViewById(R.id.txtRowDate)
        val startTime : TextView = itemView.findViewById(R.id.txtRowStart)
        val endTime : TextView = itemView.findViewById(R.id.txtRowEnd)

    }

}