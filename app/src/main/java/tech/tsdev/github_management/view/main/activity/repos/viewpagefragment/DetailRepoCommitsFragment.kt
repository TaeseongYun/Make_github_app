package tech.tsdev.github_management.view.main.activity.repos.viewpagefragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.detail_repo_commit_layout.*
import org.jetbrains.anko.support.v4.toast
import tech.tsdev.github_management.R
import tech.tsdev.github_management.base.recycler.model.basefragment.BaseFragment
import tech.tsdev.github_management.model.github.GithubRepository
import tech.tsdev.github_management.view.main.activity.repos.viewpagefragment.adapter.DetailRepoCommitRecyclerAdapter
import tech.tsdev.github_management.view.main.activity.repos.viewpagefragment.presenter.deailrepocommit.DetailRepoCommitContract
import tech.tsdev.github_management.view.main.activity.repos.viewpagefragment.presenter.deailrepocommit.DetailRepoCommitPresenter

class DetailRepoCommitsFragment : BaseFragment(), DetailRepoCommitContract.View {
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
        DetailRepoCommitPresenter(this@DetailRepoCommitsFragment, GithubRepository,
            detailRepoCommitRecyclerAdapter, disposable)
    }

    override fun onDestroyView() {
        super.onDestroyView()

        repo_owner_commit_recycler_view.removeOnScrollListener(onScrollListener)
    }

    private val onScrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)

            val firstItemView = (recyclerView.layoutManager as? GridLayoutManager)
                ?.findFirstVisibleItemPosition() ?: 0
            val totalRecyclerViewItem = detailRepoCommitRecyclerAdapter.itemCount
            val visibleItemCount = recyclerView.childCount

            if (!detailRepoCommitPresenter.isLoading && detailRepoCommitPresenter.nextPage
                && totalRecyclerViewItem - 8 < (firstItemView + visibleItemCount)
            ) {
                detailRepoCommitPresenter.loadRepoCommitListBaseRepoName(
                    arguments?.getString("detailRepoCommitsUrl") + "/commits"
                )
            }
        }
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
            addOnScrollListener(onScrollListener)
        }
    }
}