package tech.tsdev.github_management.view.main.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_issues.*
import org.jetbrains.anko.toast
import tech.tsdev.github_management.R
import tech.tsdev.github_management.base.recycler.model.baseactivity.BaseActivity
import tech.tsdev.github_management.model.github.GithubRepository
import tech.tsdev.github_management.network.RetrofitObject
import tech.tsdev.github_management.view.main.activity.adapter.issues.DetailRepoIssuesListRecyclerAdapter
import tech.tsdev.github_management.view.main.activity.presenter.issues.DetailRepoIssuesContract
import tech.tsdev.github_management.view.main.activity.presenter.issues.DetailRepoIssuesPresenter

class IssuesActivity : BaseActivity(), DetailRepoIssuesContract.View {
    override fun detailIssuesActivityIncludeComments(detailIssuesUrl: String?, detailCommentsUrl: String?) {
        Intent(this, IssuesDetailActivity::class.java).apply {
            putExtra("issuesUrl", detailIssuesUrl)
            putExtra("commentsUrl", detailCommentsUrl)
            startActivity(this)
        }
    }

    override fun dismissEmptyIssuesAnimation() {
        repo_owner_issue_empty?.visibility = View.GONE
        issue_recycler_view?.visibility = View.VISIBLE
    }

    override fun showEmptyIssuesAnimation() {
        repo_owner_issue_empty?.visibility = View.VISIBLE
        issue_recycler_view?.visibility = View.GONE
    }

    override fun issuesLoadFailMessage() {
        toast("API 문서 호출 오류")
    }

    override fun issuesLoadFailMessage(message: String) {
        toast(message)
    }

    private val detailRepoIssuesListRecyclerAdapter: DetailRepoIssuesListRecyclerAdapter by lazy {
        DetailRepoIssuesListRecyclerAdapter(this)
    }

    private val detailRepoIssuesPresenter: DetailRepoIssuesPresenter by lazy {
        DetailRepoIssuesPresenter(
            this,
            GithubRepository.getInstance(RetrofitObject.githubAPI),
            detailRepoIssuesListRecyclerAdapter,
            disposable
        )
    }

    private val onScrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)

            val firstVisibleItemPosition =
                (recyclerView.layoutManager as? GridLayoutManager)?.findFirstVisibleItemPosition() ?: 0
            val totalItemCount = detailRepoIssuesListRecyclerAdapter.itemCount
            val visibleItemsCount = recyclerView.childCount

            if (!detailRepoIssuesPresenter.isLoading && detailRepoIssuesPresenter.nextPage &&
                totalItemCount - 8 < (firstVisibleItemPosition + visibleItemsCount)
            ) {
                detailRepoIssuesPresenter.loadRepoIssuesDetailBasedIssuesUrl(
                    intent.getStringExtra("repoIssuesUrl") + "/issues"
                )
            }
        }
    }

    override fun onStop() {
        super.onStop()

        issue_recycler_view.removeOnScrollListener(onScrollListener)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_issues)

        fab.setOnClickListener { finish() }

        bar.setNavigationOnClickListener {
            toast("준비중 입니다.")
        }

        detailRepoIssuesPresenter.loadRepoIssuesDetailBasedIssuesUrl(
            intent.getStringExtra("repoIssuesUrl") + "/issues"
        )

        issue_recycler_view.run {
            adapter = detailRepoIssuesListRecyclerAdapter
            layoutManager = GridLayoutManager(this@IssuesActivity, 1)
            addOnScrollListener(onScrollListener)
        }

        println("들어온 repoIssuesUrl 값 -> ${intent.getStringExtra("repoIssuesUrl")}")
    }
}
