package tech.tsdev.github_management.ui.modules.detail.search.repo.adapter.holder

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import tech.tsdev.github_management.R
import tech.tsdev.github_management.model.RepoItem

class SearchRepoHolder(context: Context, parent: ViewGroup) :
    RecyclerView.ViewHolder(LayoutInflater.from(context).inflate(R.layout.search_repo_item, parent, false)) {


    fun onBind(repoItems: RepoItem) {
        itemView.onBind(repoItems)
    }

    private fun View.onBind(repoItem: RepoItem) {
        onBind(repoItem)
    }
}