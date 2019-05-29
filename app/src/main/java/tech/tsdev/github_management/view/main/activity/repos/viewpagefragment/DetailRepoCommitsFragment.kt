package tech.tsdev.github_management.view.main.activity.repos.viewpagefragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.detail_repo_commit_layout.*
import org.jetbrains.anko.support.v4.toast
import tech.tsdev.github_management.R
import tech.tsdev.github_management.model.github.GithubRepository
import tech.tsdev.github_management.view.main.activity.repos.viewpagefragment.adapter.DetailRepoCommitRecyclerAdapter
import tech.tsdev.github_management.view.main.activity.repos.viewpagefragment.presenter.deailrepocommit.DetailRepoCommitContract
import tech.tsdev.github_management.view.main.activity.repos.viewpagefragment.presenter.deailrepocommit.DetailRepoCommitPresenter

class DetailRepoCommitsFragment : Fragment(), DetailRepoCommitContract.View {
    override fun loadFailCommitMessage() {
        toast("API 호출 오류")
    }

    override fun loadFailCommitMessage(message: String?) {
        message?.let { toast(it) }
    }

    private val detailRepoCommitRecyclerAdapter: DetailRepoCommitRecyclerAdapter by lazy {
        DetailRepoCommitRecyclerAdapter(this@DetailRepoCommitsFragment.context)
    }
    private val detailRepoCommitPresenter: DetailRepoCommitPresenter by lazy {
        DetailRepoCommitPresenter(this@DetailRepoCommitsFragment, GithubRepository, detailRepoCommitRecyclerAdapter)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.detail_repo_commit_layout, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        detailRepoCommitPresenter.loadRepoCommitListBaseRepoName(
            arguments?.getString("detailRepoCommitsUrl") + "/commits"
        )

        repo_owner_commit_recycler_view.run {
            adapter = detailRepoCommitRecyclerAdapter
            layoutManager = GridLayoutManager(this@DetailRepoCommitsFragment.context, 1)
        }
    }
}