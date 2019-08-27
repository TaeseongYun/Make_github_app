package tech.tsdev.github_management.ui.modules.detail.mine.overview.adapter

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import tech.tsdev.github_management.base.recycler.model.BaseRecyclerModel
import tech.tsdev.github_management.model.repo.UserRepoList
import tech.tsdev.github_management.ui.modules.detail.mine.overview.adapter.holder.RepoRecyclerViewHolder

class RepoRecyclerViewAdapter(private val context: Context?) : RecyclerView.Adapter<RecyclerView.ViewHolder>(),
        BaseRecyclerModel<UserRepoList> {

    override lateinit var onClick: (Int) -> Unit

    private val repoList = mutableListOf<UserRepoList>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        RepoRecyclerViewHolder(onClick, context, parent)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as RepoRecyclerViewHolder).onBind(repoList[position])
    }

    override fun addItem(repoItems: UserRepoList) {
        repoList.add(repoItems)
    }

    override fun notifyDataItems() = notifyDataSetChanged()

    override fun getItemData(position: Int): UserRepoList = repoList[position]

    override fun getItemCount(): Int = repoList.size

    override fun clearItem() {
        repoList.clear()
    }

}
