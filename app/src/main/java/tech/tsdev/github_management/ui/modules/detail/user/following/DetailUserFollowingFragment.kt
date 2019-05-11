package tech.tsdev.github_management.ui.modules.detail.user.following

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.detail_user_following.*
import org.jetbrains.anko.support.v4.toast
import tech.tsdev.github_management.R
import tech.tsdev.github_management.model.github.GithubRepository
import tech.tsdev.github_management.ui.modules.detail.user.following.adapter.UserFollowingRecyclerAdapter
import tech.tsdev.github_management.ui.modules.detail.user.following.presenter.DetailUserFollowingContract
import tech.tsdev.github_management.ui.modules.detail.user.following.presenter.DetailUserFollowingPresenter

class DetailUserFollowingFragment : Fragment(), DetailUserFollowingContract.View {
    override fun loadFailMessage() {
        toast("로드 실패")
    }

    override fun loadFailMessage(message: String) {
        toast(message)
    }

    private val userFollowingAdapter: UserFollowingRecyclerAdapter by lazy {
        UserFollowingRecyclerAdapter(this@DetailUserFollowingFragment.context)
    }

    private val detailUserFollowingPresenter: DetailUserFollowingPresenter by lazy {
        DetailUserFollowingPresenter(this@DetailUserFollowingFragment, GithubRepository, userFollowingAdapter)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.detail_user_following, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.getString("fragmentUserName")?.let{ detailUserFollowingPresenter.loadUserFollowingBasedUserName(it) }

        user_following_recycler_view.run {
            adapter = userFollowingAdapter
            layoutManager = GridLayoutManager(this@DetailUserFollowingFragment.context, 1)
        }
    }
}