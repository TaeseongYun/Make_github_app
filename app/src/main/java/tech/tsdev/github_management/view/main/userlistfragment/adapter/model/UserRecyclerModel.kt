package tech.tsdev.github_management.view.main.userlistfragment.adapter.model

import tech.tsdev.github_management.model.user.UserListData

interface UserRecyclerModel {
    fun addItem(item: UserListData)

    fun getItemCount(): Int

    fun getItem(position: Int): UserListData

    fun notifyDataItem()

    var onClick: (Int) -> Unit
}