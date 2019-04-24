package tech.tsdev.github_management.view.main.userlistfragment.adapter.holder

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_image_view.view.*
import tech.tsdev.github_management.R
import tech.tsdev.github_management.model.UserListData

class UserRecyclerHolder(context: Context, parent: ViewGroup) :
    RecyclerView.ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_image_view, parent, false)) {

    fun onBind(item: UserListData) {
        itemView.onBind(item)
    }

    fun View.onBind(item: UserListData) {
        img_unsplash_user.proflieImageLoad(item.avatar_url)
        iv_user_name.text = item.login
    }
}