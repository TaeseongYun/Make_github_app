package tech.tsdev.github_management.view.main.activity.adapter.holder

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.user_followers_item_detail.view.*
import tech.tsdev.github_management.R
import tech.tsdev.github_management.model.UserFollowersFollowingList

class MyFollowersRecyclerHolder(context: Context?, parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(context).inflate(R.layout.user_followers_item_detail, parent, false)
) {
    fun onBind(items: UserFollowersFollowingList) {
        itemView.onBind(items)
    }

    private fun View.onBind(items: UserFollowersFollowingList) {
        user_followers_img.proflieImageLoad(items.avatarUrl)
        tv_user_followers_name.text = items.login
    }
}