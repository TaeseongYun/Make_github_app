package tech.tsdev.github_management.view.main.starfragment.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import tech.tsdev.github_management.model.ReceivedEvents
import tech.tsdev.github_management.view.main.starfragment.adapter.holder.StarRecyclerHolder
import tech.tsdev.github_management.view.main.starfragment.adapter.model.StarRecyclerModel

class StarRecyclerAdapter(val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>(), StarRecyclerModel {

    val list = mutableListOf<ReceivedEvents>()

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): RecyclerView.ViewHolder =
        StarRecyclerHolder(context, parent)


    override fun getItemCount(): Int =
        list.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as StarRecyclerHolder).onBind(list[position])
    }

    override fun addItem(item: ReceivedEvents) {
        list.add(item)
    }

    override fun getItem(position: Int): ReceivedEvents =
        list[position]

    override fun nofityedItemsData() {
        notifyDataSetChanged()
    }

    override fun clearItemData() {
        list.clear()
    }
}