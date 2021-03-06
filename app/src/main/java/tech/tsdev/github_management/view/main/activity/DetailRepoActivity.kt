package tech.tsdev.github_management.view.main.activity


import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_detail_repo.*
import org.jetbrains.anko.toast
import tech.tsdev.github_management.R
import tech.tsdev.github_management.base.recycler.model.baseactivity.BaseActivity
import tech.tsdev.github_management.model.github.GithubRepository
import tech.tsdev.github_management.network.RetrofitObject
import tech.tsdev.github_management.util.getInstance
import tech.tsdev.github_management.util.tabLayoutListener
import tech.tsdev.github_management.view.main.activity.repos.presenter.DetailRepoContract
import tech.tsdev.github_management.view.main.activity.repos.presenter.DetailRepoPresenter
import tech.tsdev.github_management.view.main.activity.repos.viewpagefragment.DetailRepoActivityFragment
import tech.tsdev.github_management.view.main.activity.repos.viewpagefragment.DetailRepoCommitsFragment
import tech.tsdev.github_management.view.main.activity.repos.viewpagefragment.DetailRepoFilesFragment
import tech.tsdev.github_management.view.main.activity.repos.viewpagefragment.DetailRepoInfoFragment

class DetailRepoActivity : BaseActivity(), DetailRepoContract.View {

    // 레포지토리 디테일한 내용을 보기 위한 액티비티
    override fun dismissProgressBar() {
        loader.visibility = View.GONE
    }

    override fun loadFailedMessage() {
        toast("로드 실패")
    }

    override fun loadFailedMessage(message: String) {
        toast(message)
    }

    override fun updateToolbarImg(
        ownerAvatarImg: String, repoName: String, repoDescription: String?,
        repoToolbarTitle: String
    ) {
        user_repo_avatar_bg?.getProfileImgRepo(ownerAvatarImg)
        desc?.text = repoName
        repoDescription?.let { user_repo_description?.text = it } ?: let {
            user_repo_description?.visibility = View.GONE
        }
        toolbar_repo?.title = repoToolbarTitle
    }

    private fun addTabLayoutItem() {
        detail_repo_tab_layout.addTab(detail_repo_tab_layout.newTab().setText(R.string.repoAbout))
        detail_repo_tab_layout.addTab(detail_repo_tab_layout.newTab().setText(R.string.repoFile))
        detail_repo_tab_layout.addTab(detail_repo_tab_layout.newTab().setText(R.string.repoCommits))
        detail_repo_tab_layout.addTab(detail_repo_tab_layout.newTab().setText(R.string.repoactirivy))
    }

    private val detailRepoPresenter: DetailRepoPresenter by lazy {
        DetailRepoPresenter(
            this@DetailRepoActivity,
            GithubRepository.getInstance(RetrofitObject.githubAPI),
            disposable
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_repo)


        btn_detail_repo_close.setOnClickListener { finish() }

        detailRepoPresenter.getLoadRepoInfo(
            intent.getStringExtra("repoUrl")
        )


        addTabLayoutItem()

        detail_repo_tab_layout.addOnTabSelectedListener(
            tabLayoutListener(view_pager)
        )

        view_pager.run {
            adapter = DetailTabLayoutAdapter(supportFragmentManager)
            addOnPageChangeListener(object : TabLayout.TabLayoutOnPageChangeListener(detail_repo_tab_layout) {})
        }
    }

    inner class DetailTabLayoutAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {
        override fun getItem(position: Int): Fragment? =
            when (position) {
                0 -> {
                    DetailRepoInfoFragment()
                        .getInstance("detailRepoUrl", intent.getStringExtra("repoUrl"))
                }
                1 -> {
                    DetailRepoFilesFragment()
                }
                2 -> {
                    DetailRepoCommitsFragment().
                            getInstance("detailRepoCommitsUrl", intent.getStringExtra("repoUrl"))
                }
                3 -> {
                    DetailRepoActivityFragment()
                }
                else -> null
            }


        override fun getCount(): Int = 4

    }
}
