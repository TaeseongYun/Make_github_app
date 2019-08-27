package tech.tsdev.github_management.ui.modules.detail.mine.overview.adapter.holder

import android.content.Context
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.repo_recyclerview_item.view.*
import tech.tsdev.github_management.R
import tech.tsdev.github_management.base.recycler.model.holder.BaseRecyclerViewHolder
import tech.tsdev.github_management.model.repo.UserRepoList

class RepoRecyclerViewHolder(onClick: (Int) -> Unit, context: Context?, parent: ViewGroup) : BaseRecyclerViewHolder(
    R.layout.repo_recyclerview_item, context, parent
) {
    init {
        itemView.setOnClickListener {
            onClick(adapterPosition)
        }
    }

    fun onBind(item: UserRepoList) {
        itemView.onBind(item)
    }

    private fun View.onBind(item: UserRepoList) {
        repo_name_recycler_view.text = item.name
        user_name_recycler_view.text = item.owner.login
    }
}