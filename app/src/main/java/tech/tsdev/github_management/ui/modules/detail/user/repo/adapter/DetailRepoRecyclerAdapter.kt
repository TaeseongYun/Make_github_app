package tech.tsdev.github_management.ui.modules.detail.user.repo.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import tech.tsdev.github_management.model.UserRepoList
import tech.tsdev.github_management.ui.modules.detail.user.repo.adapter.holder.DetailRepoRecyclerHolder
import tech.tsdev.github_management.ui.modules.detail.user.repo.adapter.model.DetailRepoRecyclerModel

class DetailRepoRecyclerAdapter(val context: Context?) : RecyclerView.Adapter<RecyclerView.ViewHolder>(), DetailRepoRecyclerModel {

    override lateinit var onClick: (Int) -> Unit

    override fun notifiedItemData() {
        notifyDataSetChanged()
    }

    private val userRepoList = mutableListOf<UserRepoList>()

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): RecyclerView.ViewHolder =
        DetailRepoRecyclerHolder(onClick, context, parent)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as DetailRepoRecyclerHolder).onBind(userRepoList[position])
    }

    override fun addItem(items: UserRepoList) {
        userRepoList.add(items)
    }

    override fun getItemCount(): Int = userRepoList.size

    override fun getItem(position: Int): UserRepoList = userRepoList[position]

    override fun deleteItemsData() {
        userRepoList.clear()
    }

}