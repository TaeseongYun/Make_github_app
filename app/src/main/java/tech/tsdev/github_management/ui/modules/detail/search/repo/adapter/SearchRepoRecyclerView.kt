package tech.tsdev.github_management.ui.modules.detail.search.repo.adapter

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import tech.tsdev.github_management.model.RepoItem
import tech.tsdev.github_management.ui.modules.detail.search.repo.adapter.holder.SearchRepoHolder
import tech.tsdev.github_management.ui.modules.detail.search.repo.adapter.model.SearchRepoModel


class SearchRepoRecyclerView(val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>(),
    SearchRepoModel {

    val list = mutableListOf<RepoItem>()

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): RecyclerView.ViewHolder =
        SearchRepoHolder(context, parent)

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(parent: RecyclerView.ViewHolder, position: Int) {
        (parent as SearchRepoHolder).onBind(list[position])
    }

    override fun addItem(repoItems: RepoItem) {
        list.add(repoItems)
    }

    override fun notifyDataItems() {
        notifyDataSetChanged()
    }

    override fun getItemData(position: Int): RepoItem = list[position]
}