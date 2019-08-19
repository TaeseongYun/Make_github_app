package tech.tsdev.github_management.view.main.activity.adapter.stargazers.model

import tech.tsdev.github_management.model.repo.GetRepoStarredUserList

interface StarredUserListRecyclerModel {
    fun addItems(items: GetRepoStarredUserList)

    fun getItemCount(): Int

    fun getItems(position: Int): GetRepoStarredUserList

    fun notifyedItemData()
}