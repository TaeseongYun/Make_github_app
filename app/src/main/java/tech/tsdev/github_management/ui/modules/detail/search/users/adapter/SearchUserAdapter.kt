package tech.tsdev.github_management.ui.modules.detail.search.users.adapter

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import tech.tsdev.github_management.base.recycler.model.BaseRecyclerModel
import tech.tsdev.github_management.model.search.Item
import tech.tsdev.github_management.ui.modules.detail.search.users.adapter.holder.SearchUserHolder


class SearchUserAdapter(val context: Context?) : RecyclerView.Adapter<RecyclerView.ViewHolder>(),
    BaseRecyclerModel<Item> {
    override fun notifyDataItems() {
        notifyDataSetChanged()
    }

    override fun clearItem() = list.clear()


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
}