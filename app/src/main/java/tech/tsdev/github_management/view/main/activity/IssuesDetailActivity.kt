package tech.tsdev.github_management.view.main.activity

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_issues_detail.*
import org.jetbrains.anko.toast
import tech.tsdev.github_management.R
import tech.tsdev.github_management.base.recycler.model.baseactivity.BaseActivity
import tech.tsdev.github_management.model.github.GithubRepository
import tech.tsdev.github_management.network.RetrofitObject
import tech.tsdev.github_management.view.main.activity.adapter.comments.DetailIssuesCommentsRecyclerAdapter
import tech.tsdev.github_management.view.main.activity.presenter.detailissues.DetailIssuesSingleContract
import tech.tsdev.github_management.view.main.activity.presenter.detailissues.DetailIssuesSinglePresenter

class IssuesDetailActivity : BaseActivity(), DetailIssuesSingleContract.View {
    override fun getSingleIssuesLoadFailMessage() {
        toast("API 문서 오류")
    }

    override fun getSingleIssuesLoadFailMessage(message: String) {
        toast(message)
    }

    @SuppressLint("SetTextI18n")
    override fun getViewLoadToolbar(
        repoIsseusOwnerAvatar: String?,
        repoIssuesNum: String?,
        repoIssuesCommentNum: String?,
        repoIssuesBody: String?
    ) {
        repoIsseusOwnerAvatar?.let { detail_issues_owner_avatar.proflieImageLoad(it) }
        repoIssuesNum?.let {
            owner_issues_N.text = "Issue #$it"
        }
        repoIssuesCommentNum?.let { owner_comments_N.text = "$it comments" }
        repoIssuesBody?.let { owner_issues.text = it }
    }

    private val detailIssuesCommentsRecyclerAdapter: DetailIssuesCommentsRecyclerAdapter by lazy {
        DetailIssuesCommentsRecyclerAdapter(this)
    }

    private val detailIssuesSinglePresenter: DetailIssuesSinglePresenter by lazy {
        DetailIssuesSinglePresenter(
            this,
            GithubRepository.getInstance(RetrofitObject.githubAPI),
            detailIssuesCommentsRecyclerAdapter,
            disposable
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_issues_detail)

        detailIssuesSinglePresenter.getSingleIssues(intent.getStringExtra("issuesUrl"))
        detailIssuesSinglePresenter.getIssuesCommentsList(intent.getStringExtra("commentsUrl"))

        detail_issues_back_img.setOnClickListener { finish() }

        repo_issues_comments_recycler_view.run {
            adapter = detailIssuesCommentsRecyclerAdapter
            layoutManager = GridLayoutManager(this@IssuesDetailActivity, 1)
        }
    }
}
