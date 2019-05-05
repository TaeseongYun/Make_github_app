package tech.tsdev.github_management.ui.modules.detail.search.repo

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import tech.tsdev.github_management.R
import tech.tsdev.github_management.ui.modules.detail.search.repo.presenter.SearchRepoContract


class SearchRepoFragment : Fragment(), SearchRepoContract.View{
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
        = inflater.inflate(R.layout.pg_search_repo_layout, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}