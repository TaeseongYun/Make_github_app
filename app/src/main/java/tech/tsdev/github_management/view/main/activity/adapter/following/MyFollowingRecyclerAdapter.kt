package tech.tsdev.github_management.view.main.activity.adapter.following

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import tech.tsdev.github_management.model.user.UserFollowersFollowingList
import tech.tsdev.github_management.view.main.activity.adapter.following.holder.MyFollowingHolder
import tech.tsdev.github_management.view.main.activity.adapter.following.model.MyFollowingModel

class MyFollowingRecyclerAdapter(val context: Context?) : RecyclerView.Adapter<RecyclerView.ViewHolder>(),
    MyFollowingModel {

    private val userFollowingUserList = mutableListOf<UserFollowersFollowingList>()


    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): RecyclerView.ViewHolder =
        MyFollowingHolder(context, parent)

    override fun getItemCount(): Int = userFollowingUserList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as MyFollowingHolder).onBind(userFollowingUserList[position])
    }

    override fun addItems(items: UserFollowersFollowingList) {
        userFollowingUserList.add(items)
    }

    override fun getItems(position: Int): UserFollowersFollowingList = userFollowingUserList[position]


    override fun notifyItemsData() {
        notifyDataSetChanged()
    }

}