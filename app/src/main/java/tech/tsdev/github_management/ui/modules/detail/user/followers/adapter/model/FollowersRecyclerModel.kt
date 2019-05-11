package tech.tsdev.github_management.ui.modules.detail.user.followers.adapter.model

import tech.tsdev.github_management.model.UserFollowersFollowingList

interface FollowersRecyclerModel {
    fun addItem(items: UserFollowersFollowingList)

    fun getItemCount(): Int

    fun getItems(position: Int): UserFollowersFollowingList

    fun notifyItemData()

    fun deleteItemData()
}