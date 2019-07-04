package tech.tsdev.github_management.view.main.userlistfragment.adapter.holder

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_image_view.view.*
import tech.tsdev.github_management.R
import tech.tsdev.github_management.model.UserListData

class UserRecyclerHolder(onClick: (Int) -> Unit, context: Context, parent: ViewGroup) :
    RecyclerView.ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_image_view, parent, false)) {

    init {
        itemView.setOnClickListener {
            onClick(adapterPosition)
        }
    }
    fun onBind(item: UserListData) {
        itemView.onBind(item)
    }

    private fun View.onBind(item: UserListData) {
        img_github_user.proflieImageLoad(item.avatarUrl)
        iv_user_name.text = item.login
    }
}