package tech.tsdev.github_management.ui.modules.detail.user.repo

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.detail_user_repo.*
import org.jetbrains.anko.support.v4.toast
import tech.tsdev.github_management.R
import tech.tsdev.github_management.model.github.GithubRepository
import tech.tsdev.github_management.ui.modules.detail.user.repo.adapter.DetailRepoRecyclerAdapter
import tech.tsdev.github_management.ui.modules.detail.user.repo.presenter.DetailUserRepoContract
import tech.tsdev.github_management.ui.modules.detail.user.repo.presenter.DetailUserRepoPresenter

class DetailUserRepoFragment : Fragment(), DetailUserRepoContract.View {
    override fun loadFailMessage() {
        toast("로드 실패")
    }

    override fun loadFailMessage(message: String) {
        toast(message)
    }

    private val detailRepoRecyclerAdapter: DetailRepoRecyclerAdapter by lazy {
        DetailRepoRecyclerAdapter(this@DetailUserRepoFragment.context)
    }

    private val detailUserRepoPresenter: DetailUserRepoPresenter by lazy {
        DetailUserRepoPresenter(this@DetailUserRepoFragment, GithubRepository, detailRepoRecyclerAdapter)
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.detail_user_repo, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.getString("fragmentUserName")?.let { detailUserRepoPresenter.getUserRepoBaseUserName(it) }

        user_repo_recycler_view.run {
            adapter = detailRepoRecyclerAdapter
            layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
        }
    }
}