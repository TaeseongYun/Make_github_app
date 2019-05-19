package tech.tsdev.github_management.view.main.activity.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import tech.tsdev.github_management.model.UserFollowersFollowingList
import tech.tsdev.github_management.view.main.activity.adapter.holder.MyFollowersRecyclerHolder
import tech.tsdev.github_management.view.main.activity.adapter.model.MyFollowersRecyclerModel

class MyFollowersRecyclerAdapter(val context: Context?) : RecyclerView.Adapter<RecyclerView.ViewHolder>(),
    MyFollowersRecyclerModel {

    private val followersUser = mutableListOf<UserFollowersFollowingList>()


    override fun onCreateViewHolder(parent: ViewGroup, position: Int): RecyclerView.ViewHolder =
        MyFollowersRecyclerHolder(context, parent)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as MyFollowersRecyclerHolder).onBind(followersUser[position])
    }

    override fun addItems(items: UserFollowersFollowingList) {
        followersUser.add(items)
    }

    override fun getItems(position: Int): UserFollowersFollowingList = followersUser[position]

    override fun getItemCount(): Int = followersUser.size

    override fun notifyItemsData() {
        notifyDataSetChanged()
    }

}