package tech.tsdev.github_management.ui.modules.detail.search.repo.adapter

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import tech.tsdev.github_management.base.recycler.model.BaseRecyclerModel
import tech.tsdev.github_management.model.RepoItem
import tech.tsdev.github_management.ui.modules.detail.search.repo.adapter.holder.SearchRepoHolder



class SearchRepoRecyclerView(val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>(),
    BaseRecyclerModel<RepoItem> {

    override fun clearItem() = list.clear()

    val list = mutableListOf<RepoItem>()

    override lateinit var onClick: (Int) -> Unit

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): RecyclerView.ViewHolder =
        SearchRepoHolder(onClick, context, parent)

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