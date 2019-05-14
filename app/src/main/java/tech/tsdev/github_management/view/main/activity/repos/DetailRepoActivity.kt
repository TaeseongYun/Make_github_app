package tech.tsdev.github_management.view.main.activity.repos

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_detail_repo.*
import org.jetbrains.anko.toast
import tech.tsdev.github_management.R
import tech.tsdev.github_management.model.github.GithubRepository
import tech.tsdev.github_management.view.main.activity.repos.presenter.DetailRepoContract
import tech.tsdev.github_management.view.main.activity.repos.presenter.DetailRepoPresenter

class DetailRepoActivity : AppCompatActivity(), DetailRepoContract.View {


    override fun dismissProgressBar() {
        loader.visibility = View.GONE
    }

    override fun loadFailedMessage() {
        toast("로드 실패")
    }

    override fun loadFailedMessage(message: String) {
        toast(message)
    }

    override fun updateToolbarImg(ownerAvatarImg: String, repoName: String, repoDescription: String?,
                                  repoToolbarTitle: String) {
        user_repo_avatar_bg.getProfileImgRepo(ownerAvatarImg)
        desc.text = repoName
        repoDescription?.let { user_repo_description.setText(it) } ?: let {
            user_repo_description.text = "No Description"
        }
        toolbar_repo.title = repoToolbarTitle
    }

    private val detailRepoPresenter: DetailRepoPresenter by lazy {
        DetailRepoPresenter(this@DetailRepoActivity, GithubRepository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_repo)


        btn_detail_repo_close.setOnClickListener { finish() }

        detailRepoPresenter.getLoadRepoInfo(
            intent.getStringExtra("repoUrl")
        )

        btn_detail_repo_close.setOnClickListener { finish() }
    }
}
