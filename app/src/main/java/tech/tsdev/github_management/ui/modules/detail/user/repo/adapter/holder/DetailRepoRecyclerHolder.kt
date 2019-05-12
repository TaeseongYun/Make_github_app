package tech.tsdev.github_management.ui.modules.detail.user.repo.adapter.holder

import android.annotation.SuppressLint
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.user_repo_detail_items.view.*
import tech.tsdev.github_management.R
import tech.tsdev.github_management.model.UserRepoList

class DetailRepoRecyclerHolder(context: Context?, parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(context).inflate(R.layout.user_repo_detail_items, parent, false)
) {
    fun onBind(items: UserRepoList) {
        itemView.onBind(items)
    }

    @SuppressLint("SetTextI18n")
    fun View.onBind(items: UserRepoList) {
        user_repo_avatar.proflieImageLoad(items.owner.avatarUrl)
        tv_user_repo_name.text = items.name
        items.description?.let { tv_user_description.text = it } ?: let { tv_user_description.text = "no Description" }
        tv_user_repo_star.text = items.stargazersCount.toString()
        tv_user_fork.text = items.forksCount.toString()
        tv_user_login.text = items.owner.login
        if(items.fork)
            tv_fork_repo.visibility = View.VISIBLE
    }
}