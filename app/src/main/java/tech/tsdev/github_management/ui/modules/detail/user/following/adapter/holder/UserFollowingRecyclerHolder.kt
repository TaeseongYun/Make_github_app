package tech.tsdev.github_management.ui.modules.detail.user.following.adapter.holder

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.user_following_items_detail.view.*
import tech.tsdev.github_management.R
import tech.tsdev.github_management.model.UserFollowersFollowingList

class UserFollowingRecyclerHolder(context: Context?, parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(context).inflate(R.layout.user_following_items_detail, parent, false)
) {
    fun onBind(items: UserFollowersFollowingList) {
        itemView.onBind(items)
    }

    fun View.onBind(items: UserFollowersFollowingList) {
        tv_user_following_name.text = items.login
        user_followings_img.proflieImageLoad(items.avatarUrl)
    }
}