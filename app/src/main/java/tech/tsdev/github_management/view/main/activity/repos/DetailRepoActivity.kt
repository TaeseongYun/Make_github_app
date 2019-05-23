package tech.tsdev.github_management.view.main.activity.repos

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.view.ViewPager
import android.view.View
import kotlinx.android.synthetic.main.activity_detail_repo.*
import org.jetbrains.anko.toast
import tech.tsdev.github_management.R
import tech.tsdev.github_management.model.github.GithubRepository
import tech.tsdev.github_management.view.main.activity.repos.presenter.DetailRepoContract
import tech.tsdev.github_management.view.main.activity.repos.presenter.DetailRepoPresenter
import tech.tsdev.github_management.view.main.activity.repos.viewpagefragment.DetailRepoActivityFragment
import tech.tsdev.github_management.view.main.activity.repos.viewpagefragment.DetailRepoCommitsFragment
import tech.tsdev.github_management.view.main.activity.repos.viewpagefragment.DetailRepoFilesFragment
import tech.tsdev.github_management.view.main.activity.repos.viewpagefragment.DetailRepoInfoFragment

class DetailRepoActivity : AppCompatActivity(), DetailRepoContract.View {

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
        user_repo_avatar_bg.getProfileImgRepo(ownerAvatarImg)
        desc.text = repoName
        repoDescription?.let { user_repo_description.setText(it) } ?: let {
            user_repo_description.text = "No Description"
        }
        toolbar_repo.title = repoToolbarTitle
    }

    private fun addTabLayoutItem() {
        detail_repo_tab_layout.addTab(detail_repo_tab_layout.newTab().setText(R.string.repoAbout))
        detail_repo_tab_layout.addTab(detail_repo_tab_layout.newTab().setText(R.string.repoFile))
        detail_repo_tab_layout.addTab(detail_repo_tab_layout.newTab().setText(R.string.repoCommits))
        detail_repo_tab_layout.addTab(detail_repo_tab_layout.newTab().setText(R.string.repoactirivy))
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

        addTabLayoutItem()

        detail_repo_tab_layout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabSelected(tab: TabLayout.Tab) {
                view_pager.currentItem = tab.position
            }

        })

        view_pager.run {
            adapter = DetailTabLayoutAdapter(supportFragmentManager)
            addOnPageChangeListener(object : TabLayout.TabLayoutOnPageChangeListener(detail_repo_tab_layout){ })
        }
    }

    inner class DetailTabLayoutAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {
        override fun getItem(position: Int): Fragment? =
            when (position) {
                0 -> {
                    DetailRepoInfoFragment()
                }
                1 -> {
                    DetailRepoFilesFragment()
                }
                2 -> {
                    DetailRepoCommitsFragment()
                }
                3 -> {
                    DetailRepoActivityFragment()
                }
                else -> null
            }


        override fun getCount(): Int = 4

    }
}
