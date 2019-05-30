package tech.tsdev.github_management.view.main.activity.adapter.issues.holder

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.detail_repo_issue_items.view.*
import tech.tsdev.github_management.R
import tech.tsdev.github_management.model.GetRepoIssuesList

class DetailRepoIssuesListRecyclerHolder(context: Context?, parent: ViewGroup): RecyclerView.ViewHolder(
    LayoutInflater.from(context).inflate(R.layout.detail_repo_issue_items, parent, false)
) {
    fun onBind(items: GetRepoIssuesList) {
        itemView.onBind(items)
    }

    private fun View.onBind(items: GetRepoIssuesList) {
        repo_issue_writer_owner_avatar.proflieImageLoad(items.user.avatarUrl)

    }
}