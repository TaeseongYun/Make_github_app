package tech.tsdev.github_management.view.main.activity.adapter.stargazers

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import tech.tsdev.github_management.model.repo.GetRepoStarredUserList
import tech.tsdev.github_management.view.main.activity.adapter.stargazers.holder.StarredUserListRecyclerHolder
import tech.tsdev.github_management.view.main.activity.adapter.stargazers.model.StarredUserListRecyclerModel

class StarredUserListRecyclerAdapter(val context: Context?) : RecyclerView.Adapter<RecyclerView.ViewHolder>(),
    StarredUserListRecyclerModel {

    private val starredUserList = mutableListOf<GetRepoStarredUserList>()

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): RecyclerView.ViewHolder =
        StarredUserListRecyclerHolder(context, parent)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as StarredUserListRecyclerHolder).onBind(starredUserList[position])
    }

    override fun addItems(items: GetRepoStarredUserList) {
        starredUserList.add(items)
    }

    override fun getItemCount(): Int = starredUserList.size

    override fun getItems(position: Int): GetRepoStarredUserList = starredUserList[position]

    override fun notifyedItemData() {
        notifyDataSetChanged()
    }

}