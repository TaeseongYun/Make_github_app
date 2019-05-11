package tech.tsdev.github_management.ui.modules.detail.user.repo.adapter.holder

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import tech.tsdev.github_management.R
import tech.tsdev.github_management.model.UserRepoList

class DetailRepoRecyclerHolder(context: Context?, parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(context).inflate(R.layout.user_repo_detail_items, parent, false)
) {
    fun onBind(items: UserRepoList) {
        itemView.onBind(items)
    }

    fun View.onBind(items: UserRepoList) {

    }
}