package tech.tsdev.github_management.view.main.activity.repos.viewpagefragment.adapter

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import tech.tsdev.github_management.model.repo.GetRepoCommitList
import tech.tsdev.github_management.view.main.activity.repos.viewpagefragment.adapter.holder.DetailRepoCommitsRecyclerHolder
import tech.tsdev.github_management.view.main.activity.repos.viewpagefragment.adapter.model.DetailRepoCommitRecyclerModel


class DetailRepoCommitRecyclerAdapter(val context: Context?) : RecyclerView.Adapter<RecyclerView.ViewHolder>(),
    DetailRepoCommitRecyclerModel {

    private val userRepoCommitInfo = mutableListOf<GetRepoCommitList>()

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): RecyclerView.ViewHolder =
        DetailRepoCommitsRecyclerHolder(context, parent)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as DetailRepoCommitsRecyclerHolder).onBind(userRepoCommitInfo[position])
    }

    override fun addItems(items: GetRepoCommitList) {
        userRepoCommitInfo.add(items)
    }

    override fun getItems(position: Int): GetRepoCommitList = userRepoCommitInfo[position]

    override fun getItemCount(): Int = userRepoCommitInfo.size

    override fun notifyedDateItem() {
        notifyDataSetChanged()
    }

}