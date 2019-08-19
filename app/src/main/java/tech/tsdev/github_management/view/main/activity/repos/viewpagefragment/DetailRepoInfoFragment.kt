package tech.tsdev.github_management.view.main.activity.repos.viewpagefragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.detail_repo_info_layout.*
import org.jetbrains.anko.support.v4.toast
import tech.tsdev.github_management.R
import tech.tsdev.github_management.base.recycler.model.basefragment.BaseFragment
import tech.tsdev.github_management.model.github.GithubRepository
import tech.tsdev.github_management.util.textVisibleGone
import tech.tsdev.github_management.view.main.activity.IssuesActivity
import tech.tsdev.github_management.view.main.activity.RepoForksActivity
import tech.tsdev.github_management.view.main.activity.RepoStargazersActivity
import tech.tsdev.github_management.view.main.activity.RepoWatchersActivity
import tech.tsdev.github_management.view.main.activity.repos.viewpagefragment.presenter.detailrepoinfo.DetailRepoInfoContract
import tech.tsdev.github_management.view.main.activity.repos.viewpagefragment.presenter.detailrepoinfo.DetailRepoInfoPresenter

class DetailRepoInfoFragment : BaseFragment(), DetailRepoInfoContract.View {

    override fun getSendRepoNameRepoUrl(repoName: String?, repoUrl: String?) {
        repoName?.let { detailRepoName ->
            repoUrl?.let { detailRepoUrl ->
                arguments = Bundle().apply {
                    putString("repoName", detailRepoName)
                    putString("detailRepoUrl", detailRepoUrl)
                }
            }
        }
    }


    override fun getOwnerRepoReadme(repoReadMeUrl: String?) {
        repoReadMeUrl?.let { owner_repo_readme.loadUrl(it) } ?: let { owner_repo_readme?.visibility = View.GONE }
    }

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
        repoWatchers?.let { owner_repo_watcher.text = it } ?: let { owner_repo_watcher.textVisibleGone() }
        repoForks?.let { owner_repo_forked.text = it } ?: let { owner_repo_forked.textVisibleGone() }
        repoStargazers?.let { owner_repo_stargazer.text = it } ?: let { owner_repo_stargazer.textVisibleGone() }
        repoIssues?.let { owner_repo_issue.text = it } ?: let { owner_repo_issue.textVisibleGone() }
    }

    private val detailRepoInfoPresenter: DetailRepoInfoPresenter by lazy {
        DetailRepoInfoPresenter(this@DetailRepoInfoFragment, GithubRepository, disposable)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.detail_repo_info_layout, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val inputDetailRepoUrl = arguments?.getString("detailRepoUrl")
        println("들어온 detailRepoUrl 값 -> ${arguments?.getString("detailRepoUrl")}")


        //레포지토리 결과 값 불러오기 위한 것
        arguments?.getString("detailRepoUrl")
            ?.let { detailRepoInfoPresenter.getLoadRepoInfoBasedRepoUrl(it) }


        //레포지토리 README 불러오기 위한 코드
        detailRepoInfoPresenter.getLoadRepoReadmeBasedRepoUrl(
            arguments?.getString("detailRepoUrl") + "/readme"
        )


        owner_repo_issue_layout.setOnClickListener {
            Intent(context, IssuesActivity::class.java).apply {
                putExtra(
                    "repoIssuesUrl",
                    arguments?.getString("detailRepoUrl")
                )
                startActivity(this)
            }
        }

        owner_repo_stargazer_layout.setOnClickListener {
            Intent(context, RepoStargazersActivity::class.java).apply {
                putExtra(
                    "repoStargazersUrl",
                    arguments?.getString("detailRepoUrl")
                )
                putExtra("repoName", arguments?.getString("repoName"))
                startActivity(this)
            }
        }

        owner_repo_fork_layout.setOnClickListener {
            Intent(context, RepoForksActivity::class.java).apply {
                putExtra("repoName", arguments?.getString("repoName"))
                putExtra(
                    "repoForkUrl",
                    arguments?.getString("detailRepoUrl")
                )
                startActivity(this)
            }
        }

        owner_repo_watcher_layout.setOnClickListener {
            Intent(context, RepoWatchersActivity::class.java).apply {
                putExtra("repoName",arguments?.getString("repoName"))
                putExtra(
                    "repoWatcherUrl",
                    arguments?.getString("detailRepoUrl")
                )
                startActivity(this)
            }
        }
    }
}