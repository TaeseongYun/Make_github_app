package tech.tsdev.github_management.view.main.activity.adapter.model

import tech.tsdev.github_management.model.UserFollowersFollowingList

interface MyFollowersRecyclerModel {
    fun addItems(items: UserFollowersFollowingList)

    fun getItems(position: Int): UserFollowersFollowingList

    fun getItemCount(): Int

    fun notifyItemsData()
}