package tech.tsdev.github_management.ui.modules.detail.user.following.adapter

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import tech.tsdev.github_management.model.UserFollowersFollowingList
import tech.tsdev.github_management.ui.modules.detail.user.following.adapter.holder.UserFollowingRecyclerHolder
import tech.tsdev.github_management.ui.modules.detail.user.following.adapter.model.UserFollowingRecyclerModel

class UserFollowingRecyclerAdapter(val context: Context?) : RecyclerView.Adapter<RecyclerView.ViewHolder>(),
    UserFollowingRecyclerModel {

    private val userFollowingList = mutableListOf<UserFollowersFollowingList>()

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): RecyclerView.ViewHolder =
        UserFollowingRecyclerHolder(context, parent)

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