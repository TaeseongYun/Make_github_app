package tech.tsdev.github_management.view.main.activity.adapter.comments.holder

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.detail_repo_issue_items.view.*
import tech.tsdev.github_management.R
import tech.tsdev.github_management.model.GetIssuesComments


class DetailIssuesCommentsRecyclerHolder(context: Context?, parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(context).inflate(R.layout.detail_repo_comments_items, parent, false)
) {
    fun onBind(items: GetIssuesComments) {
        itemView.onBind(items)
    }

    private fun View.onBind(items: GetIssuesComments) {
        repo_issue_writer_owner_avatar.proflieImageLoad(items.user.avatarUrl)
        repo_issue_writer_owner_login.text = items.user.login
        repo_issue_writer_owner_message.text = items.body
    }
}