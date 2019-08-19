package tech.tsdev.github_management.view.main.activity.adapter.followers

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import tech.tsdev.github_management.model.user.UserFollowersFollowingList
import tech.tsdev.github_management.view.main.activity.adapter.followers.holder.MyFollowersRecyclerHolder
import tech.tsdev.github_management.view.main.activity.adapter.followers.model.MyFollowersRecyclerModel

class MyFollowersRecyclerAdapter(val context: Context?) : RecyclerView.Adapter<RecyclerView.ViewHolder>(),
    MyFollowersRecyclerModel {

    private val followersUser = mutableListOf<UserFollowersFollowingList>()


    override fun onCreateViewHolder(parent: ViewGroup, position: Int): RecyclerView.ViewHolder =
        MyFollowersRecyclerHolder(
            context,
            parent
        )

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