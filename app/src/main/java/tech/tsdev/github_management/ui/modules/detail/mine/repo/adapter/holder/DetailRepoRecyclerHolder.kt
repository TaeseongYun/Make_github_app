package tech.tsdev.github_management.ui.modules.detail.mine.repo.adapter.holder

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.user_repo_detail_items.view.*
import tech.tsdev.github_management.R
import tech.tsdev.github_management.base.recycler.model.holder.BaseRecyclerViewHolder
import tech.tsdev.github_management.model.repo.UserRepoList

class DetailRepoRecyclerHolder(onClick:(Int) -> Unit, context: Context?, parent: ViewGroup) : BaseRecyclerViewHolder(
    R.layout.user_repo_detail_items, context, parent
) {

    init {
        itemView.setOnClickListener {
            onClick(adapterPosition)
        }
    }

    fun onBind(items: UserRepoList) {
        itemView.onBind(items)
    }

    @SuppressLint("SetTextI18n")
    fun View.onBind(items: UserRepoList) {
        user_repo_avatar.proflieImageLoad(items.owner.avatarUrl)
        tv_user_repo_name.text = items.name
        items.description?.let { tv_user_description.text = it } ?: let { tv_user_description.visibility = View.GONE }
        tv_user_repo_star.text = items.stargazersCount.toString()
        tv_user_fork.text = items.forksCount.toString()
        tv_user_login.text = items.owner.login
        tv_user_repo_language.text = items.language
        items.showForkedRepo(tv_fork_repo, items.fork)
    }
}