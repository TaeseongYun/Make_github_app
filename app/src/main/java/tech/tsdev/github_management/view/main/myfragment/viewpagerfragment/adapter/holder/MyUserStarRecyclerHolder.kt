package tech.tsdev.github_management.view.main.myfragment.viewpagerfragment.adapter.holder

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.user_starred_repo_items.view.*
import tech.tsdev.github_management.R
import tech.tsdev.github_management.model.GetUserStarred

class MyUserStarRecyclerHolder(onClick: (Int) -> Unit, context: Context?, parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(context).inflate(R.layout.user_starred_repo_items, parent, false)
) {

    init {
        itemView.setOnClickListener {
            onClick(adapterPosition)
        }
    }

    fun onBind(items: GetUserStarred) {
        itemView.onBind(items)
    }


    fun View.onBind(items: GetUserStarred) {
        user_starred_owner_img.proflieImageLoad(items.owner.avatarUrl)
        repo_owner_name.text = items.name
        repo_owner_language.text = items.language
        repo_owner_description.text = items.description
        repo_owner_give_star_tv.text = items.stargazersCount.toString()
        repo_owner_fork_tv.text = items.forksCount.toString()
        repo_owner_name_tv.text = items.owner.login
    }
}