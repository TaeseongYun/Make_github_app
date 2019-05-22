package tech.tsdev.github_management.view.main.activity.adapter.following.model

import tech.tsdev.github_management.model.UserFollowersFollowingList

interface MyFollowingModel {
    fun addItems(items: UserFollowersFollowingList)

    fun getItems(position: Int): UserFollowersFollowingList

    fun getItemCount(): Int

    fun notifyItemsData()
}