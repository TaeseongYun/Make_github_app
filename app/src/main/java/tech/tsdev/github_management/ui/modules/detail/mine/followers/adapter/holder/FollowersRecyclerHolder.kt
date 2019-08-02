package tech.tsdev.github_management.ui.modules.detail.mine.followers.adapter.holder

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.user_followers_item_detail.view.*
import tech.tsdev.github_management.R
import tech.tsdev.github_management.base.recycler.model.holder.BaseRecyclerViewHolder
import tech.tsdev.github_management.model.UserFollowersFollowingList

class FollowersRecyclerHolder(onClick: (Int) -> Unit, context: Context?, parent: ViewGroup) : BaseRecyclerViewHolder(
    R.layout.user_followers_item_detail, context, parent
) {
    init {
        itemView.setOnClickListener {
            onClick(adapterPosition)
        }
    }

    fun onBind(items: UserFollowersFollowingList) {
        itemView.onBind(items)
    }

    private fun View.onBind(items: UserFollowersFollowingList) {
        user_followers_img.proflieImageLoad(items.avatarUrl)
        tv_user_followers_name.text = items.login
    }
}