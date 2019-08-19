package tech.tsdev.github_management.view.main.activity.repos.viewpagefragment.adapter.model

import tech.tsdev.github_management.model.repo.GetRepoCommitList

interface DetailRepoCommitRecyclerModel {
    fun addItems(items: GetRepoCommitList)

    fun getItems(position: Int): GetRepoCommitList

    fun getItemCount(): Int

    fun notifyedDateItem()
}