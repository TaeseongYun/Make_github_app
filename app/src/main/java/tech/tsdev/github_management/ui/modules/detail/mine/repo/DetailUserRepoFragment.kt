package tech.tsdev.github_management.ui.modules.detail.mine.repo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import kotlinx.android.synthetic.main.detail_user_repo.*
import org.jetbrains.anko.support.v4.toast
import tech.tsdev.github_management.R
import tech.tsdev.github_management.base.recycler.model.basefragment.BaseFragment
import tech.tsdev.github_management.model.github.GithubRepository
import tech.tsdev.github_management.network.RetrofitObject
import tech.tsdev.github_management.ui.modules.detail.mine.repo.adapter.DetailRepoRecyclerAdapter
import tech.tsdev.github_management.ui.modules.detail.mine.repo.presenter.DetailUserRepoContract
import tech.tsdev.github_management.ui.modules.detail.mine.repo.presenter.DetailUserRepoPresenter
import tech.tsdev.github_management.util.getInstance
import tech.tsdev.github_management.view.main.activity.DetailRepoActivity

class DetailUserRepoFragment : BaseFragment(), DetailUserRepoContract.View {
    override fun showEmptyRepositoryLottie() {
        repo_empty_lottieView.visibility = View.VISIBLE
        user_repo_recycler_view.visibility = View.GONE
    }

    companion object {
        fun getInstance(key: String = "", value: String = "") =
                DetailUserRepoFragment().apply {
                    arguments = Bundle().apply {
                        putString(key, value)
                    }
                }
    }
    override fun justViewToast() {
        toast("BaseRecyclerModel 적용")
    }

    override fun getLoadDetailMyRepository(repoUrl: String) {
        context?.getInstance<DetailRepoActivity>(
            "repoUrl", repoUrl
        )
    }

    override fun loadFailMessage() {
        toast("로드 실패")
    }

    override fun loadFailMessage(message: String) {
        toast(message)
    }

    private val detailRepoRecyclerAdapter: DetailRepoRecyclerAdapter by lazy {
        DetailRepoRecyclerAdapter(this@DetailUserRepoFragment.context)
    }

    private val detailUserRepoPresenter: DetailUserRepoPresenter by lazy {
        DetailUserRepoPresenter(
            this@DetailUserRepoFragment,
            GithubRepository.getInstance(RetrofitObject.githubAPI),
            detailRepoRecyclerAdapter,
            disposable
        )
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.detail_user_repo, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.getString("fragmentUserName")?.let { detailUserRepoPresenter.getUserRepoBaseUserName(it) }

        user_repo_recycler_view.run {
            adapter = detailRepoRecyclerAdapter
            layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
        }
    }
}