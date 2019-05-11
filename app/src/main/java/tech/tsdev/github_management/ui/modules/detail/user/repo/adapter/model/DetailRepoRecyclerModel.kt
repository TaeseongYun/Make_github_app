package tech.tsdev.github_management.ui.modules.detail.user.repo.adapter.model

import tech.tsdev.github_management.model.UserRepoList

interface DetailRepoRecyclerModel {
    fun addItem(items: UserRepoList)

    fun getItemCount(): Int

    fun getItem(position: Int): UserRepoList

    fun deleteItemsData()

    fun nofityedItemData()
}