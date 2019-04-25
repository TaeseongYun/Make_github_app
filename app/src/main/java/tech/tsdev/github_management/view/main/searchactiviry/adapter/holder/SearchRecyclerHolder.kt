package tech.tsdev.github_management.view.main.searchactiviry.adapter.holder

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.detail_user_search.view.*
import tech.tsdev.github_management.R
import tech.tsdev.github_management.model.Item

class SearchRecyclerHolder(context: Context, parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(context).inflate(R.layout.user_search_item, parent, false)
) {

    fun onBind(itemData: Item) {
        itemView.onBind(itemData)
    }

    fun View.onBind(itemData: Item) {
        img_user.proflieImageLoad(itemData.avatar_url)
        tv_user_name.text = itemData.login
        tv_repo_many.text = itemData.starred_url
        tv_star_many.text = itemData.repos_url
    }
}