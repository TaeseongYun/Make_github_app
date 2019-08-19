package tech.tsdev.github_management.view.main.activity.adapter.repolist

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import tech.tsdev.github_management.model.repo.UserRepoList
import tech.tsdev.github_management.view.main.activity.adapter.repolist.holder.MyRepoListRecyclerHolder
import tech.tsdev.github_management.view.main.activity.adapter.repolist.model.MyRepoListRecyclerModel

class MyRepoListRecyclerAdapter(val context: Context?) : RecyclerView.Adapter<RecyclerView.ViewHolder>(),
    MyRepoListRecyclerModel {

    private val myRepoList = mutableListOf<UserRepoList>()

    override lateinit var onClick: (Int) -> Unit

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): RecyclerView.ViewHolder =
        MyRepoListRecyclerHolder(onClick, context, parent)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as MyRepoListRecyclerHolder).onBind(myRepoList[position])
    }

    override fun addItem(items: UserRepoList) {
        myRepoList.add(items)
    }

    override fun getItemCount(): Int = myRepoList.size

    override fun getItem(position: Int): UserRepoList = myRepoList[position]

    override fun notifyItemData() {
        notifyDataSetChanged()
    }

}