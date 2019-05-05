package tech.tsdev.github_management.ui.modules.detail.search.repo.adapter.model

import tech.tsdev.github_management.model.RepoItem

interface SearchRepoModel {
    fun addItem(repoItems: RepoItem)

    fun notifyDataItems()

    fun getItemData(position: Int): RepoItem

    fun getItemCount(): Int
}