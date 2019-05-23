package tech.tsdev.github_management.view.main.activity.adapter.repolist.model

import tech.tsdev.github_management.model.UserRepoList

interface MyRepoListRecyclerModel {
    fun addItem(items: UserRepoList)

    fun getItemCount(): Int

    fun getItem(position: Int): UserRepoList

    fun notifyItemData()

}