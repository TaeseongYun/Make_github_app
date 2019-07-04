package tech.tsdev.github_management.ui.modules.detail.search.users.adapter

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import tech.tsdev.github_management.model.Item
import tech.tsdev.github_management.ui.modules.detail.search.users.adapter.holder.SearchUserHolder
import tech.tsdev.github_management.ui.modules.detail.search.users.adapter.model.SearchUserModel

class SearchUserAdapter(val context: Context?) : RecyclerView.Adapter<RecyclerView.ViewHolder>(), SearchUserModel {

    val list = mutableListOf<Item>()


    override lateinit var onClick: (Int) -> Unit

    override fun onCreateViewHolder(parent: ViewGroup, ppsition: Int): RecyclerView.ViewHolder =
        SearchUserHolder(onClick, context!!, parent)


    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as SearchUserHolder).onBind(list[position])
    }

    override fun addItem(items: Item) {
        list.add(items)
    }

    override fun getItemData(position: Int): Item = list[position]


    override fun notifyDataItem() {
        notifyDataSetChanged()
    }

}