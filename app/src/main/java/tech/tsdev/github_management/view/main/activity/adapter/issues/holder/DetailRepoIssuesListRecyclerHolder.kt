package tech.tsdev.github_management.view.main.activity.adapter.issues.holder

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.detail_repo_issue_items.view.*
import tech.tsdev.github_management.R
import tech.tsdev.github_management.model.repo.GetRepoIssuesList

class DetailRepoIssuesListRecyclerHolder(onClick: (Int) -> Unit, context: Context?, parent: ViewGroup): RecyclerView.ViewHolder(
    LayoutInflater.from(context).inflate(R.layout.detail_repo_issue_items, parent, false)
) {
    init {
        itemView.setOnClickListener {
            onClick(adapterPosition)
        }
    }
    fun onBind(items: GetRepoIssuesList) {
        itemView.onBind(items)
    }

    @SuppressLint("SetTextI18n")
    private fun View.onBind(items: GetRepoIssuesList) {
        repo_issue_writer_owner_avatar.proflieImageLoad(items.user.avatarUrl)
        repo_issue_writer_owner_login.text = items.user.login
        repo_issue_writer_owner_message.text = items.title
        repo_owner_writerN.text = "#${items.number}"
        repo_owner_issue_commentN.text = items.comments.toString()
    }
}