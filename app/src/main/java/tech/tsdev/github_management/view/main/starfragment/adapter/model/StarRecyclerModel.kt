package tech.tsdev.github_management.view.main.starfragment.adapter.model

import tech.tsdev.github_management.model.ReceivedEvents

interface StarRecyclerModel {

    fun addItem(item: ReceivedEvents)

    fun getItem(position: Int): ReceivedEvents

    fun getItemCount(): Int

    fun nofityedItemsData()

    fun clearItemData()

    var onClick: (Int) -> Unit
}