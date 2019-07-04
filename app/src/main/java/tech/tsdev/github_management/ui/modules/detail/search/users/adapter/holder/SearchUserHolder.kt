package tech.tsdev.github_management.ui.modules.detail.search.users.adapter.holder

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.pg_search_user_detail.view.*
import tech.tsdev.github_management.R
import tech.tsdev.github_management.model.Item


class SearchUserHolder(onClick: (Int)-> Unit, context: Context, parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(context).inflate(
        R.layout.pg_search_user_detail, parent, false
    )
) {

    init {
        itemView.setOnClickListener {
            onClick(adapterPosition)
        }
    }
    fun onBind(singleUserItem: Item) {
        itemView.onBind(singleUserItem)
    }

    private fun View.onBind(singleUserItem: Item) {
        img_search_user_avatar.proflieImageLoad(singleUserItem.avatarUrl)
        tv_search_user_name.text = singleUserItem.login
    }
}