package tech.tsdev.github_management.view.main.activity.adapter.watcher

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import tech.tsdev.github_management.model.repo.GetRepoSubscribers
import tech.tsdev.github_management.view.main.activity.adapter.watcher.holder.RepoWatcherRecyclerHolder
import tech.tsdev.github_management.view.main.activity.adapter.watcher.model.RepoWatcherRecyclerModel

class RepoWatcherRecyclerAdapter(val context: Context?) : RecyclerView.Adapter<RecyclerView.ViewHolder>(),
    RepoWatcherRecyclerModel {

    private val repoWatcherUserList = mutableListOf<GetRepoSubscribers>()

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): RecyclerView.ViewHolder =
        RepoWatcherRecyclerHolder(context, parent)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as RepoWatcherRecyclerHolder).onBind(repoWatcherUserList[position])
    }

    override fun addItems(items: GetRepoSubscribers) {
        repoWatcherUserList.add(items)
    }

    override fun getItemCount(): Int = repoWatcherUserList.size

    override fun getItems(position: Int): GetRepoSubscribers = repoWatcherUserList[position]

    override fun notifiedDataItems() {
        notifyDataSetChanged()
    }

}