package tech.tsdev.github_management.ui.modules.detail.search.searchactiviry.adapter.holder

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.detail_user_search.view.*
import tech.tsdev.github_management.R
import tech.tsdev.github_management.model.Item



class SearchRecyclerHolder(onClick:(Int) -> Unit, context: Context, parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(context).inflate(R.layout.user_search_item, parent, false)
) {

    init {
        itemView.setOnClickListener {
            onClick(adapterPosition)
        }
    }

    fun onBind(itemData: Item) {
        itemView.onBind(itemData)
    }

    private fun View.onBind(itemData: Item) {
        img_github_search_user.proflieImageLoad(itemData.avatarUrl)
        tv_github_user_name.text = itemData.login
    }
}