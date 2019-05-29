package tech.tsdev.github_management.view.main.activity.repos.viewpagefragment.adapter.holder

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.repo_commit_detail_items.view.*
import tech.tsdev.github_management.R
import tech.tsdev.github_management.model.GetRepoCommitList

class DetailRepoCommitsRecyclerHolder(context: Context?, parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(context).inflate(R.layout.repo_commit_detail_items, parent, false)
){
    fun onBind(items: GetRepoCommitList){
        itemView.onBind(items)
    }

    private fun View.onBind(items: GetRepoCommitList){
        user_commit_owner_img.proflieImageLoad(items.committer.avatarUrl)
        repo_committer_name.text = items.committer.login
        repo_committer_message.text = items.commit.message
        repo_committer_hash_code.text = items.sha.substring(0..6)
        repo_committer_issue.text = items.commit.commentCount.toString()
    }
}