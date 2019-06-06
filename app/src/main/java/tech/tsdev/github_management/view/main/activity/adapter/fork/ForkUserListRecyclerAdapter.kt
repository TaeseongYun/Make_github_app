package tech.tsdev.github_management.view.main.activity.adapter.fork

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import tech.tsdev.github_management.model.GetForkUserList
import tech.tsdev.github_management.view.main.activity.adapter.fork.holder.ForkUserListRecyclerHolder
import tech.tsdev.github_management.view.main.activity.adapter.fork.model.ForkUserListRecyclerModel

class ForkUserListRecyclerAdapter(private val context: Context?) : RecyclerView.Adapter<RecyclerView.ViewHolder>(),
    ForkUserListRecyclerModel {

    private val forkUserList = mutableListOf<GetForkUserList>()

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): RecyclerView.ViewHolder =
        ForkUserListRecyclerHolder(context, parent)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ForkUserListRecyclerHolder).onBind(forkUserList[position])
    }

    override fun addItems(items: GetForkUserList) {
        forkUserList.add(items)
    }

    override fun notifiedDataItems() {
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = forkUserList.size

    override fun getItems(position: Int): GetForkUserList = forkUserList[position]
}