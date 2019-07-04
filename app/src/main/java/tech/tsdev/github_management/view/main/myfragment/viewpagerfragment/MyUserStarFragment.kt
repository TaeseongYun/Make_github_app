package tech.tsdev.github_management.view.main.myfragment.viewpagerfragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.user_starred_repo.*
import org.jetbrains.anko.support.v4.toast
import tech.tsdev.github_management.R
import tech.tsdev.github_management.model.github.GithubRepository
import tech.tsdev.github_management.view.main.activity.DetailRepoActivity
import tech.tsdev.github_management.view.main.myfragment.viewpagerfragment.adapter.MyUserStarRecyclerAdapter
import tech.tsdev.github_management.view.main.myfragment.viewpagerfragment.presenter.mystar.MyUserStarContract
import tech.tsdev.github_management.view.main.myfragment.viewpagerfragment.presenter.mystar.MyUserStarPresenter

class MyUserStarFragment : Fragment(), MyUserStarContract.View {

    override fun getDetailRepoBasedUserGivedStar(repoUrl: String) {
        Intent(activity, DetailRepoActivity::class.java).apply {
            putExtra("repoUrl", repoUrl)
            startActivity(this)
        }
    }

    override fun loadFailRepoMessage() {
        toast("로드 실패")
    }

    private val userRecyclerViewAdapter: MyUserStarRecyclerAdapter by lazy {
        MyUserStarRecyclerAdapter(this@MyUserStarFragment.context)
    }

    private val myUserStarPresenter: MyUserStarPresenter by lazy {
        MyUserStarPresenter(
            this@MyUserStarFragment,
            GithubRepository,
            userRecyclerViewAdapter
        )
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.user_starred_repo, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.getString("userInfoName")?.let { myUserStarPresenter.getUserGiveStarBasedOnUserName(it) }
        user_repo_starred_recycler_view.run {
            adapter = userRecyclerViewAdapter
            layoutManager = GridLayoutManager(context, 1)
        }
    }
}