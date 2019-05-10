package tech.tsdev.github_management.view.main.starfragment.adapter.holder

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.user_activities_fragment_items.view.*
import tech.tsdev.github_management.R
import tech.tsdev.github_management.model.ReceivedEvents

class StarRecyclerHolder(context: Context?, parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(context).inflate(
        R.layout.user_activities_fragment_items, parent, false
    )
){
    fun onBind(items: ReceivedEvents) {
        itemView.onBind(items)
    }

    private fun View.onBind(items: ReceivedEvents) {
        user_activities_img.proflieImageLoad(items.actor.avatarUrl)
        user_actor_login.text = items.actor.login
        user_event_tv.text = items.whichStarRepoORCreateRepo(items.type)
    }
}