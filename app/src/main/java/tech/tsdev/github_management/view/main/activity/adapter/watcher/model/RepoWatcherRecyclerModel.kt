package tech.tsdev.github_management.view.main.activity.adapter.watcher.model

import tech.tsdev.github_management.model.repo.GetRepoSubscribers

interface RepoWatcherRecyclerModel {
    fun addItems(items: GetRepoSubscribers)

    fun getItemCount(): Int

    fun getItems(position: Int): GetRepoSubscribers

    fun notifiedDataItems()
}