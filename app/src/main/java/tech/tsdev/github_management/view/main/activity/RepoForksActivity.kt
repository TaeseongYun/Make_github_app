package tech.tsdev.github_management.view.main.activity


import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_repo_forks.*
import org.jetbrains.anko.toast
import tech.tsdev.github_management.R
import tech.tsdev.github_management.base.recycler.model.baseactivity.BaseActivity
import tech.tsdev.github_management.model.github.GithubRepository
import tech.tsdev.github_management.view.main.activity.adapter.fork.ForkUserListRecyclerAdapter
import tech.tsdev.github_management.view.main.activity.presenter.fork.ForkUserListContract
import tech.tsdev.github_management.view.main.activity.presenter.fork.ForkUserListPresenter

class RepoForksActivity : BaseActivity(), ForkUserListContract.View {
    override fun forkUserListLoadFailMessage() {
        toast("API 문서 호출 오류")
    }

    override fun forkUserListLoadFailMessage(message: String) {
        toast(message)
    }

    override fun showNothingUserLottieAni() {
        repo_fork_empty_lottie_ani?.visibility = View.VISIBLE
        repo_fork_user_recycler_view?.visibility = View.GONE
    }

    private val forkUserListRecyclerAdapter: ForkUserListRecyclerAdapter by lazy {
        ForkUserListRecyclerAdapter(this)
    }

    private val forkUserListPresenter: ForkUserListPresenter by lazy {
        ForkUserListPresenter(this, GithubRepository, forkUserListRecyclerAdapter, disposable)
    }

    override fun onStop() {
        super.onStop()

        repo_fork_user_recycler_view.removeOnScrollListener(onScrollListener)
    }

    private val onScrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)

            val totalItemCount = forkUserListRecyclerAdapter.itemCount
            val firstVisibleItems =
                (recyclerView.layoutManager as? GridLayoutManager)?.findFirstVisibleItemPosition() ?: 0
            val visibleItems = repo_fork_user_recycler_view.childCount

            if(!forkUserListPresenter.isLoading && forkUserListPresenter.nextPage &&
                    totalItemCount - 8 < (firstVisibleItems + visibleItems)
            ){
                forkUserListPresenter.getLoadForkUserListBasedForkUrl(
                    intent.getStringExtra("repoForkUrl") + "/forks"
                )
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repo_forks)

        full_name.text = intent.getStringExtra("repoName")
        repo_fork_close_img.setOnClickListener { finish() }

        forkUserListPresenter.getLoadForkUserListBasedForkUrl(
            intent.getStringExtra("repoForkUrl") + "/forks"
        )

        repo_fork_user_recycler_view.run {
            adapter = forkUserListRecyclerAdapter
            layoutManager = GridLayoutManager(this.context, 1)
            addOnScrollListener(onScrollListener)
        }
    }
}
