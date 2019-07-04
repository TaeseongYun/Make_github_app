package tech.tsdev.github_management.view.main.activity


import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_repo_stargazers.*
import org.jetbrains.anko.toast
import tech.tsdev.github_management.R
import tech.tsdev.github_management.model.github.GithubRepository
import tech.tsdev.github_management.view.main.activity.adapter.stargazers.StarredUserListRecyclerAdapter
import tech.tsdev.github_management.view.main.activity.presenter.stargazers.RepoStarredUserListContract
import tech.tsdev.github_management.view.main.activity.presenter.stargazers.RepoStarredUserListPresenter

class RepoStargazersActivity : AppCompatActivity(), RepoStarredUserListContract.View {
    override fun loadFailGithubApi() {
        toast("API 문서 호출 오류")
    }

    override fun loadFailGithubApi(message: String) {
        toast(message)
    }

    override fun emptyStargazerUserList() {
        user_stargazer_recycler_view?.visibility = View.GONE
        repo_stargazers_empty_lottie_ani?.visibility = View.VISIBLE
    }

    private val starredUserListRecyclerAdapter: StarredUserListRecyclerAdapter by lazy {
        StarredUserListRecyclerAdapter(this)
    }
    private val repoStarredUserListPresenter: RepoStarredUserListPresenter by lazy {
        RepoStarredUserListPresenter(this, GithubRepository, starredUserListRecyclerAdapter)
    }


    private val onScrollRecyclerViewListener = object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)

            val firstItem = (recyclerView.layoutManager as? GridLayoutManager)?.findFirstVisibleItemPosition() ?: 0
            val visibleItemCount = recyclerView.childCount
            val totalItemsCount = starredUserListRecyclerAdapter.itemCount

            if (!repoStarredUserListPresenter.isLoading && totalItemsCount - 1 < (firstItem + visibleItemCount)) {
                println("스크롤 뷰 이벤트 들어옴")
                repoStarredUserListPresenter.loadRepoStarredUserList(
                    intent.getStringExtra("repoStargazersUrl") + "/stargazers"
                )
            }

        }
    }

    override fun onStop() {
        super.onStop()

        user_stargazer_recycler_view?.removeOnScrollListener(onScrollRecyclerViewListener)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repo_stargazers)

        repo_stargazers_back_img.setOnClickListener { finish() }
        repo_name.text = intent.getStringExtra("repoName")

        repoStarredUserListPresenter.loadRepoStarredUserList(
            intent.getStringExtra("repoStargazersUrl") + "/stargazers"
        )

        user_stargazer_recycler_view.run {
            adapter = starredUserListRecyclerAdapter
            layoutManager = GridLayoutManager(this.context, 1)
            addOnScrollListener(onScrollRecyclerViewListener)
        }
    }
}
