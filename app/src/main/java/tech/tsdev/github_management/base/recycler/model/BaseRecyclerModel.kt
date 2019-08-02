package tech.tsdev.github_management.base.recycler.model

interface BaseRecyclerModel<ITEM: Any?> {
    fun addItem(repoItems: ITEM)

    fun notifyDataItems()

    fun getItemData(position: Int): ITEM

    fun getItemCount(): Int

    var onClick: (Int) -> Unit

    fun clearItem()
}