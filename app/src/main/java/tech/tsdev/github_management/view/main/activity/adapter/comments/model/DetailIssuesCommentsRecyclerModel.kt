package tech.tsdev.github_management.view.main.activity.adapter.comments.model

import tech.tsdev.github_management.model.comment.GetIssuesComments

interface DetailIssuesCommentsRecyclerModel {
    fun addItems(items: GetIssuesComments)

    fun getItemCount(): Int

    fun getItems(position: Int): GetIssuesComments

    fun nofityedItemData()
}