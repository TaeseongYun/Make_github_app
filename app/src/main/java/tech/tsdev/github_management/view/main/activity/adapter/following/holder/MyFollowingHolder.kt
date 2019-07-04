package tech.tsdev.github_management.view.main.activity.adapter.following.holder

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.user_following_items_detail.view.*
import tech.tsdev.github_management.R
import tech.tsdev.github_management.model.UserFollowersFollowingList

class MyFollowingHolder(context: Context?, parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(context).inflate(R.layout.user_following_items_detail, parent, false)
) {
    fun onBind(items: UserFollowersFollowingList) {
        itemView.onBind(items)
    }

    private fun View.onBind(items: UserFollowersFollowingList) {
        user_followings_img.proflieImageLoad(items.avatarUrl)
        tv_user_following_name.text = items.login
    }
}