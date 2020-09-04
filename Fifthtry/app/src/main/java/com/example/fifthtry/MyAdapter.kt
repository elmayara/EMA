package com.example.fifthtry

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.resit.R
import com.example.sqlite.BestPlays


class MyAdapter(/*it dont need parameters, you dummy */):
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var Plays = listOf<BestPlays>()
    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvName = itemView.findViewById(R.id.poiName) as TextView
        val tvDescrip = itemView.findViewById(R.id.poiDescription) as TextView

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType:Int) : RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return MyViewHolder(layoutInflater.inflate(R.layout.list_view, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, index: Int) {
        val myViewHolder = holder as MyViewHolder
        holder.tvName.text = Plays[index].name
        holder.tvDescrip.text = Plays[index].id.toString()+Plays[index].date+Plays[index].time
    }
    override fun getItemCount(): Int {
        return Plays.size
    }
}