package tech.tsdev.github_management.ui.modules.detail.user.followers.adapter.model

import tech.tsdev.github_management.model.UserFollowersList

interface FollowersRecyclerModel {
    fun addItem(items: UserFollowersList)

    fun getItemCount(): Int

    fun getItems(position: Int): UserFollowersList

    fun notifyItemData()

    fun deleteItemData()
}