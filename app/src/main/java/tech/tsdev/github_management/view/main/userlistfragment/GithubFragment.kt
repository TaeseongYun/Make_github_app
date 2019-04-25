package tech.tsdev.github_management.view.main.userlistfragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.userlist_fragment.*
import tech.tsdev.github_management.R
import tech.tsdev.github_management.model.github.GithubRepository
import tech.tsdev.github_management.view.main.userlistfragment.adapter.UserListRecyclerAdapter
import tech.tsdev.github_management.view.main.userlistfragment.presenter.GithubContract
import tech.tsdev.github_management.view.main.userlistfragment.presenter.GithubPresenter


class GithubFragment : Fragment(), GithubContract.View {
    override fun showProgressbar() {

    }

    override fun dissmissProgressbar() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun loadFailMessage() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun loadFailMessage(message: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private val userRecyclerAdapter: UserListRecyclerAdapter by lazy {
        UserListRecyclerAdapter(this@GithubFragment.context)
    }
    private val gitPresenter: GithubPresenter by lazy {
        GithubPresenter(this@GithubFragment, GithubRepository, userRecyclerAdapter)
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