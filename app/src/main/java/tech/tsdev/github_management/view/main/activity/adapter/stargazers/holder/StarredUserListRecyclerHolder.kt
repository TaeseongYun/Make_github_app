package tech.tsdev.github_management.view.main.activity.adapter.stargazers.holder

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.user_followers_item_detail.view.*
import tech.tsdev.github_management.R
import tech.tsdev.github_management.model.repo.GetRepoStarredUserList

class StarredUserListRecyclerHolder(context: Context?, parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(context).inflate(R.layout.user_followers_item_detail, parent, false)
) {
    fun onBind(items: GetRepoStarredUserList) {
        itemView.onBind(items)
    }

    private fun View.onBind(items: GetRepoStarredUserList){
        user_followers_img.proflieImageLoad(items.avatarUrl)
        tv_user_followers_name.text = items.login
    }
}