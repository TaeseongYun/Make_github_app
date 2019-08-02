package tech.tsdev.github_management.ui.modules.detail.mine.followers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.detail_user_followers.*
import org.jetbrains.anko.support.v4.toast
import tech.tsdev.github_management.R
import tech.tsdev.github_management.model.github.GithubRepository
import tech.tsdev.github_management.ui.modules.detail.mine.followers.adapter.FollowersRecyclerAdapter
import tech.tsdev.github_management.ui.modules.detail.mine.followers.presenter.DetailUserFollowersContract
import tech.tsdev.github_management.ui.modules.detail.mine.followers.presenter.DetailUserFollowersPresenter

class DetailUserFollowersFragment : Fragment(), DetailUserFollowersContract.View{
    override fun justShowToast() {
        toast("BaseRecyclerViewModel 구현")
    }

    override fun loadFailUserFollowersMessage() {
        toast("로드 실패")
    }

    override fun loadFailUserFollowersMessage(message: String) {
        toast(message)
    }

    private val detailUserFollowerPresenter: DetailUserFollowersPresenter by lazy {
        DetailUserFollowersPresenter(this@DetailUserFollowersFragment, GithubRepository, followersRecyclerAdapter)
    }
    private val followersRecyclerAdapter: FollowersRecyclerAdapter by lazy {
        FollowersRecyclerAdapter(this@DetailUserFollowersFragment.context)
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.detail_user_followers, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.getString("fragmentUserName")?.let { detailUserFollowerPresenter.getFollowersBasedOnUserName(it) }
        user_followers_recycler_view.run {
            adapter = followersRecyclerAdapter
            layoutManager = GridLayoutManager(this@DetailUserFollowersFragment.context, 1)
        }
    }
}