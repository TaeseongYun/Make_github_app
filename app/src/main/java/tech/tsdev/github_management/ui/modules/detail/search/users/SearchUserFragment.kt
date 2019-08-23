package tech.tsdev.github_management.ui.modules.detail.search.users

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.pg_search_user_layout.*
import org.jetbrains.anko.support.v4.toast
import tech.tsdev.github_management.R
import tech.tsdev.github_management.base.recycler.model.basefragment.BaseFragment
import tech.tsdev.github_management.model.github.GithubRepository
import tech.tsdev.github_management.network.RetrofitObject
import tech.tsdev.github_management.ui.modules.detail.DetailActivity
import tech.tsdev.github_management.ui.modules.detail.search.users.adapter.SearchUserAdapter
import tech.tsdev.github_management.ui.modules.detail.search.users.presenter.SearchUserContract
import tech.tsdev.github_management.ui.modules.detail.search.users.presenter.SearchUserPresenter

class SearchUserFragment : BaseFragment(), SearchUserContract.View {
    override fun loadSearchUserDetail(userLogin: String) {
        Intent(context, DetailActivity::class.java).apply {
            putExtra("userName", userLogin)
            startActivity(this)
        }
    }

    override fun loadFailShowMessage() {
        toast("API 문서 오류")
    }

    override fun loadFailShowMessage(message: String) {
        toast(message)
    }

    private val searchUserAdapter: SearchUserAdapter by lazy {
        SearchUserAdapter(this@SearchUserFragment.context)
    }

    private val searchUsePresenter: SearchUserPresenter by lazy {
        SearchUserPresenter(
            this@SearchUserFragment,
            GithubRepository.getInstance(RetrofitObject.githubAPI),
            searchUserAdapter,
            disposable
        )
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.pg_search_user_layout, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        searchUsePresenter.getSearchUserForQuery(arguments?.getString("searchUserName"))

        search_user_recycler_view.run {
            adapter = searchUserAdapter
            layoutManager = GridLayoutManager(context, 1)
        }
    }
}