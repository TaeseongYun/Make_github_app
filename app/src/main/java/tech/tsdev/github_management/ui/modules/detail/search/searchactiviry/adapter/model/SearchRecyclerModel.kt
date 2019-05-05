package tech.tsdev.github_management.ui.modules.detail.search.searchactiviry.adapter.model

import tech.tsdev.github_management.model.Item

interface SearchRecyclerModel {
    fun getItemData(position: Int): Item

    fun notifyDataChanged()

    fun getItemCount(): Int

    fun addItem(itemData: Item)

    fun clearItem()

    var onClick: (Int) -> Unit
}