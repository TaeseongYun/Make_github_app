package tech.tsdev.github_management.ui.modules.detail.user.followers.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import tech.tsdev.github_management.model.UserFollowersList
import tech.tsdev.github_management.ui.modules.detail.user.followers.adapter.holder.FollowersRecyclerHolder
import tech.tsdev.github_management.ui.modules.detail.user.followers.adapter.model.FollowersRecyclerModel

class FollowersRecyclerAdapter(val contet: Context?) : RecyclerView.Adapter<RecyclerView.ViewHolder>(),
    FollowersRecyclerModel {

    private val followersList = mutableListOf<UserFollowersList>()


    override fun deleteItemData() {
        followersList.clear()
    }

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): RecyclerView.ViewHolder
       = FollowersRecyclerHolder(contet, parent)



    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as FollowersRecyclerHolder).onBind(followersList[position])
    }

    override fun addItem(items: UserFollowersList) {
        followersList.add(items)
    }

    override fun getItemCount(): Int = followersList.size

    override fun getItems(position: Int): UserFollowersList = followersList[position]

    override fun notifyItemData() {
        notifyDataSetChanged()
    }

}