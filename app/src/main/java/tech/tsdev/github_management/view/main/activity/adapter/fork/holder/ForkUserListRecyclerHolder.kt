package tech.tsdev.github_management.view.main.activity.adapter.fork.holder

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.user_following_items_detail.view.*
import tech.tsdev.github_management.R
import tech.tsdev.github_management.model.fork.GetForkUserList

class ForkUserListRecyclerHolder(context: Context?, parent: ViewGroup): RecyclerView.ViewHolder(
    LayoutInflater.from(context).inflate(R.layout.user_following_items_detail, parent, false)
) {
    fun onBind(items: GetForkUserList) {
        itemView.onBind(items)
    }

    private fun View.onBind(items: GetForkUserList) {
        user_followings_img.proflieImageLoad(items.owner.avatarUrl)
        tv_user_following_name.text = items.owner.login
    }
}