package tech.tsdev.github_management.view.main.activity.repos.viewpagefragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.detail_repo_info_layout.*
import org.jetbrains.anko.support.v4.toast
import tech.tsdev.github_management.R
import tech.tsdev.github_management.model.github.GithubRepository
import tech.tsdev.github_management.util.textVisibleGone
import tech.tsdev.github_management.view.main.activity.repos.viewpagefragment.presenter.detailrepoinfo.DetailRepoInfoContract
import tech.tsdev.github_management.view.main.activity.repos.viewpagefragment.presenter.detailrepoinfo.DetailRepoInfoPresenter

class DetailRepoInfoFragment : Fragment(), DetailRepoInfoContract.View {
    override fun loadFailMessage() {
        toast("API 로드 실패")
    }

    override fun loadFailMessage(message: String) {
        toast(message)
    }

    override fun showDetailRepoInfo(
        repoName: String?,
        repoCreateAt: String?,
        repoIssues: String?,
        repoStargazers: String?,
        repoForks: String?,
        repoWatchers: String?
    ) {
        repoName?.let { owner_repo_name.text = it } ?: let { owner_repo_name.textVisibleGone() }
        repoCreateAt?.let { owner_repo_create_At.text = it } ?: let { owner_repo_create_At.textVisibleGone() }
    }

    private val detailRepoInfoPresenter: DetailRepoInfoPresenter by lazy {
        DetailRepoInfoPresenter(this@DetailRepoInfoFragment, GithubRepository)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.detail_repo_info_layout, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.getString("detailRepoInfoUrl")?.let { detailRepoInfoPresenter.getLoadRepoInfoBasedRepoUrl(it) }
    }
}