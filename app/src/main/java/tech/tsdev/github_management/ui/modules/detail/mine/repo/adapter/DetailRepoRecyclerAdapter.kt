package tech.tsdev.github_management.ui.modules.detail.mine.repo.adapter

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import tech.tsdev.github_management.base.recycler.model.BaseRecyclerModel
import tech.tsdev.github_management.model.UserRepoList
import tech.tsdev.github_management.ui.modules.detail.mine.repo.adapter.holder.DetailRepoRecyclerHolder

class DetailRepoRecyclerAdapter(val context: Context?) : RecyclerView.Adapter<RecyclerView.ViewHolder>(),
    BaseRecyclerModel<UserRepoList> {
    override fun notifyDataItems() {
        notifyDataSetChanged()
    }

    override fun getItemData(position: Int): UserRepoList = userRepoList[position]

    override fun clearItem() = userRepoList.clear()

    override lateinit var onClick: (Int) -> Unit


    private val userRepoList = mutableListOf<UserRepoList>()

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): RecyclerView.ViewHolder =
        DetailRepoRecyclerHolder(onClick, context, parent)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as DetailRepoRecyclerHolder).onBind(userRepoList[position])
    }

    override fun addItem(items: UserRepoList) {
        userRepoList.add(items)
    }

    override fun getItemCount(): Int = userRepoList.size
}