package tech.tsdev.github_management.ui.modules.detail.user.following.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import tech.tsdev.github_management.model.UserFollowersFollowingList
import tech.tsdev.github_management.ui.modules.detail.user.following.adapter.holder.UserFollowingRecyclerHolder
import tech.tsdev.github_management.ui.modules.detail.user.following.adapter.model.UserFollowingRecyclerModel

class UserFollowingRecyclerAdapter(val context: Context?) : RecyclerView.Adapter<RecyclerView.ViewHolder>(),
    UserFollowingRecyclerModel {

    private val userFollowingList = mutableListOf<UserFollowersFollowingList>()

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RecyclerView.ViewHolder =
        UserFollowingRecyclerHolder(context)

    override fun getItemCount(): Int = userFollowingList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as UserFollowingRecyclerHolder).onBind(userFollowingList[position])
    }

    override fun addItem(items: UserFollowersFollowingList) {
        userFollowingList.add(items)
    }

    override fun getItems(position: Int): UserFollowersFollowingList = userFollowingList[position]

    override fun notifyItemsDate() {
        notifyDataSetChanged()
    }

    override fun deleteItemDate() {
        userFollowingList.clear()
    }

}