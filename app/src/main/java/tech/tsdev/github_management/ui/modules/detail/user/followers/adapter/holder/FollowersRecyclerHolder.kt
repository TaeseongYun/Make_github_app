package tech.tsdev.github_management.ui.modules.detail.user.followers.adapter.holder

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.user_follwers_item_detail.view.*
import tech.tsdev.github_management.R
import tech.tsdev.github_management.model.UserFollowersFollowingList

class FollowersRecyclerHolder(context: Context?, parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(context).inflate(R.layout.user_follwers_item_detail, parent, false)
) {
    fun onBind(items: UserFollowersFollowingList) {
        itemView.onBind(items)
    }

    fun View.onBind(items: UserFollowersFollowingList) {
        user_followers_img.proflieImageLoad(items.avatarUrl)
        tv_user_followers_name.text = items.login
    }
}