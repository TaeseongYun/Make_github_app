package tech.tsdev.github_management.ui.modules.detail.search.users.adapter.model

import tech.tsdev.github_management.model.Item


interface SearchUserModel {
    fun addItem(items: Item)

    fun getItemData(position: Int): Item

    fun getItemCount(): Int

    fun notifyDataItem()

    var onClick: (Int) -> Unit
}