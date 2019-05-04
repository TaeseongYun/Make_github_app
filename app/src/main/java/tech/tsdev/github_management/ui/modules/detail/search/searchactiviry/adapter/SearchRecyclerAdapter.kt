package tech.tsdev.github_management.ui.modules.detail.search.searchactiviry.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import tech.tsdev.github_management.model.Item
import tech.tsdev.github_management.ui.modules.detail.search.searchactiviry.adapter.holder.SearchRecyclerHolder
import tech.tsdev.github_management.ui.modules.detail.search.searchactiviry.adapter.model.SearchRecyclerModel

class SearchRecyclerAdapter(val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>(),
    SearchRecyclerModel {


    private val list = mutableListOf<Item>()

    override lateinit var onClick: (Int) -> Unit


    override fun clearItem() = list.clear()

    override fun getItemCount(): Int = list.size

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): RecyclerView.ViewHolder {
        return SearchRecyclerHolder(onClick, context, parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as? SearchRecyclerHolder)?.onBind(list[position])
    }

    override fun getItemData(position: Int): Item = list[position]

    override fun notifyDataChanged() {
        notifyDataSetChanged()
    }


    override fun addItem(itemData: Item) {
        list.add(itemData)
    }

}