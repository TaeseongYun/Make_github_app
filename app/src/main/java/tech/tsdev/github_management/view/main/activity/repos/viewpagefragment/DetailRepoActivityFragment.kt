package tech.tsdev.github_management.view.main.activity.repos.viewpagefragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import tech.tsdev.github_management.R

class DetailRepoActivityFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.detail_repo_activity_layout, container, false)
}