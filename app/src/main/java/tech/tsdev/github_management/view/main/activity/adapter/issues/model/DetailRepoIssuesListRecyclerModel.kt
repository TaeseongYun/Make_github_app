package tech.tsdev.github_management.view.main.activity.adapter.issues.model

import tech.tsdev.github_management.model.GetRepoIssuesList

interface DetailRepoIssuesListRecyclerModel{
    fun addItems(items: GetRepoIssuesList)

    fun getItemCount(): Int

    fun getItems(position: Int): GetRepoIssuesList

    fun notifiedItemData()
}