package tech.tsdev.github_management.view.main.myfragment.viewpagerfragment.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import tech.tsdev.github_management.model.GetUserStarred
import tech.tsdev.github_management.view.main.myfragment.viewpagerfragment.adapter.holder.MyUserStarRecyclerHolder
import tech.tsdev.github_management.view.main.myfragment.viewpagerfragment.adapter.model.MyUserStarModel

class MyUserStarRecyclerAdapter(val context: Context?) : RecyclerView.Adapter<RecyclerView.ViewHolder>(),
    MyUserStarModel {

    private val userStarList = mutableListOf<GetUserStarred>()


    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): RecyclerView.ViewHolder =
        MyUserStarRecyclerHolder(context, parent)


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as MyUserStarRecyclerHolder).onBind(userStarList[position])
    }

    override fun additem(item: GetUserStarred) {
        userStarList.add(item)
    }

    override fun getItemCount(): Int = userStarList.size

    override fun getItem(position: Int): GetUserStarred = userStarList[position]

    override fun notifyItemData() {
        notifyDataSetChanged()
    }

}