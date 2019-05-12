package tech.tsdev.github_management.ui.modules.detail

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.view.ViewGroup
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.toolbar_user_info.*
import tech.tsdev.github_management.R
import tech.tsdev.github_management.ui.modules.detail.user.followers.DetailUserFollowersFragment
import tech.tsdev.github_management.ui.modules.detail.user.following.DetailUserFollowingFragment
import tech.tsdev.github_management.ui.modules.detail.user.overview.DetailUserOverviewFragment
import tech.tsdev.github_management.ui.modules.detail.user.repo.DetailUserRepoFragment
import tech.tsdev.github_management.ui.modules.detail.search.searchactiviry.SearchActivity

class DetailActivity : AppCompatActivity() {



    private val viewAdapter: DetailViewPagerAdapter by lazy {
        DetailViewPagerAdapter(supportFragmentManager, this)
    }

    private fun addTabLayoutItem() {
        tab_layout.addTab(tab_layout.newTab().setText(R.string.overview))
        tab_layout.addTab(tab_layout.newTab().setText(R.string.repository))
        tab_layout.addTab(tab_layout.newTab().setText(R.string.followers))
        tab_layout.addTab(tab_layout.newTab().setText(R.string.following))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)


        tv_user_name.text = intent.getStringExtra( SearchActivity.USER_NAME )



        println("userName -> ${intent.getStringExtra( SearchActivity.USER_NAME )}")

        addTabLayoutItem()

        img_close_btn.setOnClickListener {
            finish()
        }



        // 뷰페이저에서 어뎁터 붙혀주고 addOnPageChangeListener 는 페이지가 변경 될때 Tab도 같이 변하게 묶어주는 리스너
        view_pager.run {
            adapter = viewAdapter
            addOnPageChangeListener(object : TabLayout.TabLayoutOnPageChangeListener(tab_layout){})
        }

        //탭 레이아웃 클릭 할때 해당하는 포지션의 fragment를 보여줌 (즉 바인딩 해줌)
        tab_layout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabReselected(p0: TabLayout.Tab?) {

            }

            override fun onTabUnselected(p0: TabLayout.Tab?) {

            }

            override fun onTabSelected(tab: TabLayout.Tab) {
                view_pager.currentItem = tab.position
            }

        })
    }

    inner class DetailViewPagerAdapter(fm: FragmentManager, val context: Context) : FragmentStatePagerAdapter(fm) {


        override fun getItem(position: Int): Fragment? =
            when (position) {
                0 -> {
                    DetailUserOverviewFragment().apply {
                        arguments = Bundle().apply {
                            putString("fragmentUserName", intent.getStringExtra("userName"))
                        }
                    }
                }
                1 -> DetailUserRepoFragment().apply {
                    arguments = Bundle().apply {
                        putString("fragmentUserName", intent.getStringExtra("userName"))
                    }
                }
                2 -> DetailUserFollowersFragment().apply {
                    arguments = Bundle().apply {
                        putString("fragmentUserName", intent.getStringExtra("userName"))
                    }
                }
                3 -> DetailUserFollowingFragment().apply {
                    arguments = Bundle().apply {
                        putString("fragmentUserName", intent.getStringExtra("userName"))
                    }
                }
                else -> null
            }




        override fun getCount(): Int = 4

        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
            super.destroyItem(container, position, `object`)
        }

    }
}
