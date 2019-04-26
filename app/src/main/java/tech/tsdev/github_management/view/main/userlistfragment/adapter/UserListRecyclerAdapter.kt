package tech.tsdev.github_management.view.main.userlistfragment.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import tech.tsdev.github_management.model.UserListData
import tech.tsdev.github_management.view.main.userlistfragment.adapter.holder.UserRecyclerHolder
import tech.tsdev.github_management.view.main.userlistfragment.adapter.model.UserRecyclerModel

class UserListRecyclerAdapter(private val context: Context?) : RecyclerView.Adapter<RecyclerView.ViewHolder>(), UserRecyclerModel {

    val list = mutableListOf<UserListData>()

    override lateinit var onClick: (Int) -> Unit

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): RecyclerView.ViewHolder {
        return UserRecyclerHolder(onClick, context!!, parent)
    }
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as? UserRecyclerHolder)?.onBind(list[position])
    }

    override fun addItem(item: UserListData) {
        list.add(item)
    }

    override fun getItemCount(): Int = list.size

    override fun getItem(position: Int): UserListData = list[position]

    override fun notifyDataItem() {
        notifyDataSetChanged()
    }
}