package tech.tsdev.github_management.ui.modules.detail.search.repo.adapter.holder

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.search_repo_item.view.*
import tech.tsdev.github_management.R
import tech.tsdev.github_management.model.RepoItem

class SearchRepoHolder(context: Context, parent: ViewGroup) :
    RecyclerView.ViewHolder(LayoutInflater.from(context).inflate(R.layout.search_repo_item, parent, false)) {


    fun onBind(repoItems: RepoItem) {
        itemView.onBind(repoItems)
    }

    private fun View.onBind(repoItem: RepoItem) {
        repo_user_avatar.proflieImageLoad(repoItem.owner.avatarUrl)
        search_repo_forked.text = repoItem.forks.toString()
        search_repo_language.text = repoItem.language
        search_repo_stared.text = repoItem.stargazersCount.toString()
        repo_full_name.text = repoItem.fullName
    }
}