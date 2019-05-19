package tech.tsdev.github_management.view.main.myfragment.viewpagerfragment.adapter.model

import tech.tsdev.github_management.model.GetUserStarred

interface MyUserStarModel {
    fun additem(item: GetUserStarred)

    fun getItemCount(): Int

    fun getItem(position: Int): GetUserStarred

    fun notifyItemData()

    var onClick: (Int) -> Unit
}