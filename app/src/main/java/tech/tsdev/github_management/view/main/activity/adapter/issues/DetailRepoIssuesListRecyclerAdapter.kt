package tech.tsdev.github_management.view.main.activity.adapter.issues

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import tech.tsdev.github_management.model.GetRepoIssuesList
import tech.tsdev.github_management.view.main.activity.adapter.issues.holder.DetailRepoIssuesListRecyclerHolder
import tech.tsdev.github_management.view.main.activity.adapter.issues.model.DetailRepoIssuesListRecyclerModel

class DetailRepoIssuesListRecyclerAdapter(val context: Context?) : RecyclerView.Adapter<RecyclerView.ViewHolder>(),
    DetailRepoIssuesListRecyclerModel {

    private val userRepoIssueList = mutableListOf<GetRepoIssuesList>()

    override lateinit var onClick: (Int) -> Unit
    override fun onCreateViewHolder(parent: ViewGroup, position: Int): RecyclerView.ViewHolder =
        DetailRepoIssuesListRecyclerHolder(onClick, context, parent)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as DetailRepoIssuesListRecyclerHolder).onBind(userRepoIssueList[position])
    }

    override fun addItems(items: GetRepoIssuesList) {
        userRepoIssueList.add(items)
    }

    override fun getItemCount(): Int = userRepoIssueList.size

    override fun getItems(position: Int): GetRepoIssuesList = userRepoIssueList[position]

    override fun notifiedItemData() {
        notifyDataSetChanged()
    }
}