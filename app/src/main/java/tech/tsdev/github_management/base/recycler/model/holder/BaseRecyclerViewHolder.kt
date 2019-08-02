package tech.tsdev.github_management.base.recycler.model.holder

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerViewHolder(@LayoutRes layout: Int, context: Context?, parent: ViewGroup) :
    RecyclerView.ViewHolder(
        LayoutInflater.from(context).inflate(layout, parent, false)
    )