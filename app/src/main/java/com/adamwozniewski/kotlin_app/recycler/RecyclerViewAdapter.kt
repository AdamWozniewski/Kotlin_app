package com.adamwozniewski.kotlin_app.recycler

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.adamwozniewski.kotlin_app.R
import kotlinx.android.synthetic.main.list_item.view.*

class RecyclerViewAdapter(private var initRecyclerList: ArrayList<Pair<String, String>>)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): RecyclerView.ViewHolder {
        val li = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return object: RecyclerView.ViewHolder(li){}
    }

    override fun getItemCount(): Int = initRecyclerList.count()

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder.itemView.header.text = initRecyclerList[position].first // odpowałenie do ID
        holder.itemView.desc.text = initRecyclerList[position].second
    }

}
