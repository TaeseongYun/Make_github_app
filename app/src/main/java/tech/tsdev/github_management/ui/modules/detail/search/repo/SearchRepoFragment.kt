package tech.tsdev.github_management.ui.modules.detail.search.repo

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.pg_search_repo_layout.*
import tech.tsdev.github_management.R
import tech.tsdev.github_management.model.github.GithubRepository
import tech.tsdev.github_management.ui.modules.detail.search.repo.adapter.SearchRepoRecyclerView
import tech.tsdev.github_management.ui.modules.detail.search.repo.presenter.SearchRepoContract
import tech.tsdev.github_management.ui.modules.detail.search.repo.presenter.SearchRepoPresenter


class SearchRepoFragment : Fragment(), SearchRepoContract.View{
    override fun successLoadView() {
        recycler_view_search_repo.visibility = View.VISIBLE
    }

    override fun failLoadView() {
        tv_nothing_result.visibility = View.VISIBLE
    }

    private val searchRepoRecyclerView: SearchRepoRecyclerView by lazy {
        SearchRepoRecyclerView(this@SearchRepoFragment.context!!)
    }
    private val searchRepoPresenter: SearchRepoPresenter by lazy {
        SearchRepoPresenter(this@SearchRepoFragment, GithubRepository, searchRepoRecyclerView)
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View
        = inflater.inflate(R.layout.pg_search_repo_layout, container, false)




    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recycler_view_search_repo.run{
            adapter = searchRepoRecyclerView
            layoutManager = GridLayoutManager(context, 1)
        }
        searchRepoPresenter.loadRepoBasedSearchSentense(arguments?.getString("searchQuery"))
    }
}