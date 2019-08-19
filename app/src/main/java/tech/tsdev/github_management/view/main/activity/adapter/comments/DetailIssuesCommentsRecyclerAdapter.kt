package tech.tsdev.github_management.view.main.activity.adapter.comments

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import tech.tsdev.github_management.model.comment.GetIssuesComments
import tech.tsdev.github_management.view.main.activity.adapter.comments.holder.DetailIssuesCommentsRecyclerHolder
import tech.tsdev.github_management.view.main.activity.adapter.comments.model.DetailIssuesCommentsRecyclerModel

class DetailIssuesCommentsRecyclerAdapter(val context: Context?) : RecyclerView.Adapter<RecyclerView.ViewHolder>(),
    DetailIssuesCommentsRecyclerModel {

    private val detailIssuesCommentsList = mutableListOf<GetIssuesComments>()

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): RecyclerView.ViewHolder =
        DetailIssuesCommentsRecyclerHolder(context, parent)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as DetailIssuesCommentsRecyclerHolder).onBind(detailIssuesCommentsList[position])
    }

    override fun addItems(items: GetIssuesComments) {
        detailIssuesCommentsList.add(items)
    }

    override fun getItemCount(): Int = detailIssuesCommentsList.size

    override fun getItems(position: Int): GetIssuesComments = detailIssuesCommentsList[position]

    override fun nofityedItemData() {
        notifyDataSetChanged()
    }

}