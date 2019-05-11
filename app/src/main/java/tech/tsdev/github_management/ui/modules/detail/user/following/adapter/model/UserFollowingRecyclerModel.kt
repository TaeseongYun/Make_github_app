package tech.tsdev.github_management.ui.modules.detail.user.following.adapter.model

import tech.tsdev.github_management.model.UserFollowersFollowingList

interface UserFollowingRecyclerModel {
    fun addItem(items: UserFollowersFollowingList)

    fun getItemCount(): Int

    fun getItems(position: Int): UserFollowersFollowingList

    fun notifyItemsDate()

    fun deleteItemDate()
}