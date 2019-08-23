package tech.tsdev.github_management.view.main.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_my_repository_list.*
import org.jetbrains.anko.toast
import tech.tsdev.github_management.R
import tech.tsdev.github_management.base.recycler.model.baseactivity.BaseActivity
import tech.tsdev.github_management.model.github.GithubRepository
import tech.tsdev.github_management.network.RetrofitObject
import tech.tsdev.github_management.view.main.activity.adapter.repolist.MyRepoListRecyclerAdapter
import tech.tsdev.github_management.view.main.activity.presenter.repolist.MyRepoListContract
import tech.tsdev.github_management.view.main.activity.presenter.repolist.MyRepoListPresenter

class MyRepositoryListActivity : BaseActivity(), MyRepoListContract.View {
    override fun getRepoDetailView(repoName: String?) {
        Intent(this, DetailRepoActivity::class.java).apply {
            putExtra("repoUrl", repoName)
            startActivity(this)
        }
    }

    override fun showLoadFailToastMessage(message: String) {
        toast(message)
    }

    override fun showLoadFailToastMessage() {
        toast("API 로드 실패")
    }

    private val myRepoListPresenter: MyRepoListPresenter by lazy {
        MyRepoListPresenter(
            this,
            GithubRepository.getInstance(RetrofitObject.githubAPI),
            myRepoListRecyclerAdapter,
            disposable
        )
    }

    private val myRepoListRecyclerAdapter: MyRepoListRecyclerAdapter by lazy {
        MyRepoListRecyclerAdapter(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_repository_list)

        user_repo_close_btn.setOnClickListener { finish() }

        tv_repo_list_owner_name.text = intent.getStringExtra("userRepoListBasedOnUserName")

        myRepoListPresenter.getUserRepoListBasedUserName(intent.getStringExtra("userRepoListBasedOnUserName"))

        user_repo_activity_recycler_view.run {
            adapter = myRepoListRecyclerAdapter
            layoutManager = GridLayoutManager(this.context, 1)

        }
    }
}
