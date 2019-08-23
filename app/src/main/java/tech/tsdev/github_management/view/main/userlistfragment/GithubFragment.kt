package tech.tsdev.github_management.view.main.userlistfragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.faltenreich.skeletonlayout.Skeleton
import com.faltenreich.skeletonlayout.applySkeleton
import com.faltenreich.skeletonlayout.createSkeleton
import kotlinx.android.synthetic.main.pg_search_repo_layout.*
import kotlinx.android.synthetic.main.userlist_fragment.*
import org.jetbrains.anko.support.v4.toast
import tech.tsdev.github_management.R
import tech.tsdev.github_management.base.recycler.model.basefragment.BaseFragment
import tech.tsdev.github_management.model.github.GithubRepository
import tech.tsdev.github_management.network.RetrofitObject
import tech.tsdev.github_management.ui.modules.detail.DetailActivity
import tech.tsdev.github_management.view.main.userlistfragment.adapter.UserListRecyclerAdapter
import tech.tsdev.github_management.view.main.userlistfragment.presenter.GithubContract
import tech.tsdev.github_management.view.main.userlistfragment.presenter.GithubPresenter


class GithubFragment : BaseFragment(), GithubContract.View {

    override fun loadDetailUser(userName: String) {
        Intent(context, DetailActivity::class.java).apply {
            putExtra("userName", userName)
            startActivity(this)
        }
    }


    override fun showProgressbar() {
        user_recycler_view?.visibility = View.GONE
        user_list_progress_layout?.visibility = View.VISIBLE

    }

    override fun dissmissProgressbar() {
        user_recycler_view?.visibility = View.VISIBLE
        user_list_progress_layout?.visibility = View.GONE
    }

    override fun loadFailMessage() {
        toast("로드에 실패 하였습니다.")
    }

    override fun loadFailMessage(message: String) {
        toast(message)
    }

    private val userRecyclerAdapter: UserListRecyclerAdapter by lazy {
        UserListRecyclerAdapter(this@GithubFragment.context)
    }
    private val gitPresenter: GithubPresenter by lazy {
        GithubPresenter(
            this@GithubFragment,
            GithubRepository.getInstance(RetrofitObject.githubAPI),
            userRecyclerAdapter,
            disposable
        )
    }

    private val onScrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)

            val visibleItemCount = recyclerView.childCount
            val totalItem = userRecyclerAdapter.itemCount
            val firstItem = (user_recycler_view.layoutManager as? GridLayoutManager)
                ?.findFirstVisibleItemPosition() ?: 0

            if (!gitPresenter.isLoading && totalItem - 30 <= (firstItem + visibleItemCount)) {
                gitPresenter.loadGithubUser()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        user_recycler_view.removeOnScrollListener(onScrollListener)
        Log.d("onDestroyView", "프래그먼트 화면 사라짐")
//        gitPresenter.loadGithubUser()
        view?.let {
            val parentViewGroup = it.parent as ViewGroup
            parentViewGroup.removeAllViews()
        }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.userlist_fragment, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        gitPresenter.loadGithubUser()

        user_recycler_view.run {
            adapter = userRecyclerAdapter
            layoutManager = GridLayoutManager(context, 1)
            addOnScrollListener(onScrollListener)
        }
    }

}