package tech.tsdev.github_management.ui.modules.detail.mine.followers.adapter

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import tech.tsdev.github_management.base.recycler.model.BaseRecyclerModel
import tech.tsdev.github_management.model.user.UserFollowersFollowingList
import tech.tsdev.github_management.ui.modules.detail.mine.followers.adapter.holder.FollowersRecyclerHolder


class FollowersRecyclerAdapter(private val content: Context?) : RecyclerView.Adapter<RecyclerView.ViewHolder>(),
    BaseRecyclerModel<UserFollowersFollowingList> {

    override fun notifyDataItems() {
        notifyDataSetChanged()
    }

    override lateinit var onClick: (Int) -> Unit

    override fun getItemData(position: Int): UserFollowersFollowingList = followersList[position]

    override fun clearItem() =
        followersList.clear()


    private val followersList = mutableListOf<UserFollowersFollowingList>()




    override fun onCreateViewHolder(parent: ViewGroup, position: Int): RecyclerView.ViewHolder
       = FollowersRecyclerHolder(onClick, content, parent)



    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as FollowersRecyclerHolder).onBind(followersList[position])
    }

    override fun addItem(followersItems: UserFollowersFollowingList) {
        followersList.add(followersItems)
    }

    override fun getItemCount(): Int = followersList.size



}