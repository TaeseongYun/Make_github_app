package tech.tsdev.github_management.view.main.activity.adapter.fork.model

import tech.tsdev.github_management.model.fork.GetForkUserList

interface ForkUserListRecyclerModel {

    fun addItems(items: GetForkUserList)

    fun notifiedDataItems()

    fun getItemCount(): Int

    fun getItems(position: Int): GetForkUserList
}