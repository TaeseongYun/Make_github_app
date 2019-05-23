package tech.tsdev.github_management.view.main.activity.adapter.repolist.holder

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.user_repo_detail_items.view.*
import tech.tsdev.github_management.R
import tech.tsdev.github_management.model.UserRepoList

class MyRepoListRecyclerHolder(onClick:(Int) -> Unit, context: Context?, parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(context).inflate(R.layout.user_repo_detail_items, parent, false)
) {
    init {
        itemView.setOnClickListener {
            onClick(adapterPosition)
        }
    }

    fun onBind(items: UserRepoList) {
        itemView.onBind(items)
    }

    private fun View.onBind(items: UserRepoList) {
        user_repo_avatar.proflieImageLoad(items.owner.avatarUrl)
        tv_user_repo_name.text = items.name
        tv_user_description.text = items.description
        tv_user_repo_language.text = items.language
        tv_user_repo_star.text = items.stargazersCount.toString()
        tv_user_fork.text = items.forksCount.toString()
        tv_user_login.text = items.owner.login
    }
}