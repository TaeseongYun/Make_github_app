package tech.tsdev.github_management.ui.modules.detail.mine.following.adapter.holder

import android.content.Context
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.user_following_items_detail.view.*
import tech.tsdev.github_management.R
import tech.tsdev.github_management.base.recycler.model.holder.BaseRecyclerViewHolder
import tech.tsdev.github_management.model.user.UserFollowersFollowingList

class UserFollowingRecyclerHolder(onClick:(Int) -> Unit, context: Context?, parent: ViewGroup) : BaseRecyclerViewHolder(
    R.layout.user_following_items_detail, context, parent
){
    init {
        itemView.setOnClickListener {
            onClick(adapterPosition)
        }
    }

    fun onBind(items: UserFollowersFollowingList) {
        itemView.onBind(items)
    }

    fun View.onBind(items: UserFollowersFollowingList) {
        tv_user_following_name.text = items.login
        user_followings_img.proflieImageLoad(items.avatarUrl)
    }
}