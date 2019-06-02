package tech.tsdev.github_management.view.main.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.View
import kotlinx.android.synthetic.main.activity_issues.*
import org.jetbrains.anko.toast
import tech.tsdev.github_management.R
import tech.tsdev.github_management.model.github.GithubRepository
import tech.tsdev.github_management.view.main.activity.adapter.issues.DetailRepoIssuesListRecyclerAdapter
import tech.tsdev.github_management.view.main.activity.presenter.issues.DetailRepoIssuesContract
import tech.tsdev.github_management.view.main.activity.presenter.issues.DetailRepoIssuesPresenter

class IssuesActivity : AppCompatActivity(), DetailRepoIssuesContract.View {
    override fun detailIssuesActivity(detailIssuesUrl: String?) {
        Intent(this, IssuesDetailActivity::class.java).apply {
            putExtra("issuesUrl", detailIssuesUrl)
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
        DetailRepoIssuesPresenter(this, GithubRepository, detailRepoIssuesListRecyclerAdapter)
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
        }

    }
}
