package tech.tsdev.github_management.view.main.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.View
import kotlinx.android.synthetic.main.activity_repo_watchers.*
import org.jetbrains.anko.toast
import tech.tsdev.github_management.R
import tech.tsdev.github_management.model.github.GithubRepository
import tech.tsdev.github_management.view.main.activity.adapter.watcher.RepoWatcherRecyclerAdapter
import tech.tsdev.github_management.view.main.activity.presenter.watcher.RepoWatcherContract
import tech.tsdev.github_management.view.main.activity.presenter.watcher.RepoWatcherPresenter

class RepoWatchersActivity : AppCompatActivity(), RepoWatcherContract.View {
    override fun loadWatcherUserErrorMessage() {
        toast("API 문서 오류")
    }

    override fun loadWatcherUserErrorMessage(message: String) {
        toast(message)
    }

    override fun showRepoSubscribeEmptyUser() {
        repo_watcher_recycler_view?.visibility = View.GONE
        repo_watcher_empty_lottie_ani?.visibility = View.VISIBLE
    }

    private val repoWatcherRecyclerAdapter: RepoWatcherRecyclerAdapter by lazy {
        RepoWatcherRecyclerAdapter(this)
    }

    private val repoWatcherPresenter: RepoWatcherPresenter by lazy {
        RepoWatcherPresenter(this, GithubRepository, repoWatcherRecyclerAdapter)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repo_watchers)

        repo_watcher_close_btn.setOnClickListener { finish() }
        repo_watcher_name.text = intent.getStringExtra("repoName")

        repoWatcherPresenter.loadWatcherUserListBasedRepoUrl(
            intent.getStringExtra("repoWatcherUrl") + "/subscribers"
        )

        repo_watcher_recycler_view.run {
            adapter = repoWatcherRecyclerAdapter
            layoutManager = GridLayoutManager(this.context, 1)

        }
    }
}
