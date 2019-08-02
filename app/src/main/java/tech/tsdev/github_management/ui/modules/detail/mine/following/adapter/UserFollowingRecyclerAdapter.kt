package tech.tsdev.github_management.ui.modules.detail.mine.following.adapter

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import tech.tsdev.github_management.base.recycler.model.BaseRecyclerModel
import tech.tsdev.github_management.model.UserFollowersFollowingList
import tech.tsdev.github_management.ui.modules.detail.mine.following.adapter.holder.UserFollowingRecyclerHolder

class UserFollowingRecyclerAdapter(val context: Context?) : RecyclerView.Adapter<RecyclerView.ViewHolder>(),
    BaseRecyclerModel<UserFollowersFollowingList> {
    override fun notifyDataItems() {
        notifyDataSetChanged()
    }

    override fun getItemData(position: Int): UserFollowersFollowingList = userFollowingList[position]

    override fun clearItem() = userFollowingList.clear()

    private val userFollowingList = mutableListOf<UserFollowersFollowingList>()

    override lateinit var onClick: (Int) -> Unit
    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): RecyclerView.ViewHolder =
        UserFollowingRecyclerHolder(onClick, context, parent)

    override fun getItemCount(): Int = userFollowingList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as UserFollowingRecyclerHolder).onBind(userFollowingList[position])
    }

    override fun addItem(items: UserFollowersFollowingList) {
        userFollowingList.add(items)
    }

}