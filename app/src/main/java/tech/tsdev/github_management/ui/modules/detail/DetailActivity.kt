package tech.tsdev.github_management.ui.modules.detail

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.view.Menu
import android.view.ViewGroup
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.toolbar_user_info.*
import org.jetbrains.anko.toast
import tech.tsdev.github_management.R
import tech.tsdev.github_management.ui.modules.detail.user.followers.DetailUserFollowersFragment
import tech.tsdev.github_management.ui.modules.detail.user.following.DetailUserFollowingFragment
import tech.tsdev.github_management.ui.modules.detail.user.overview.DetailUserOverviewFragment
import tech.tsdev.github_management.ui.modules.detail.user.repo.DetailUserRepoFragment
import tech.tsdev.github_management.ui.modules.detail.search.searchactiviry.SearchActivity
import tech.tsdev.github_management.util.replace

class DetailActivity : AppCompatActivity() {



    private val viewAdapter: DetailViewPagerAdapter by lazy {
        DetailViewPagerAdapter(supportFragmentManager, this)
    }

    private val detailUserOverviewFragment: DetailUserOverviewFragment by lazy {
        DetailUserOverviewFragment().apply {
            arguments = Bundle().apply {
                putString("fragmentUserName", intent.getStringExtra("userName"))
            }
        }
    }

    private val detailUserRepositoryFragment: DetailUserRepoFragment by lazy {
        DetailUserRepoFragment().apply {
            arguments = Bundle().apply {
                putString("fragmentUserName", intent.getStringExtra("userName"))
            }
        }
    }

    private val detailUserFollowersFragment: DetailUserFollowersFragment by lazy {
        DetailUserFollowersFragment().apply {
            arguments = Bundle().apply {
                putString("fragmentUserName", intent.getStringExtra("userName"))
            }
        }
    }

    private val detailUserFollowingFragment: DetailUserFollowingFragment by lazy {
        DetailUserFollowingFragment().apply {
            arguments = Bundle().apply {
                putString("fragmentUserName", intent.getStringExtra("userName"))
            }
        }
    }
    private fun addTabLayoutItem() {
//        tab_layout.addTab(tab_layout.newTab().setText(R.string.overview))
//        tab_layout.addTab(tab_layout.newTab().setText(R.string.repository))
//        tab_layout.addTab(tab_layout.newTab().setText(R.string.followers))
//        tab_layout.addTab(tab_layout.newTab().setText(R.string.following))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        setSupportActionBar(detail_user_bar)

//        detail_user_bar.replaceMenu(R.menu.bottom_app_bar)
//        tv_user_name.text = intent.getStringExtra( SearchActivity.USER_NAME )



//        addTabLayoutItem()

        fab_home.setOnClickListener {
            finish()
        }



        // 뷰페이저에서 어뎁터 붙혀주고 addOnPageChangeListener 는 페이지가 변경 될때 Tab도 같이 변하게 묶어주는 리스너
//        view_pager.run {
//            adapter = viewAdapter
//            addOnPageChangeListener(object : TabLayout.TabLayoutOnPageChangeListener(tab_layout){})
//        }
//
//        //탭 레이아웃 클릭 할때 해당하는 포지션의 fragment를 보여줌 (즉 바인딩 해줌)
//        tab_layout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
//            override fun onTabReselected(p0: TabLayout.Tab?) {
//
//            }
//
//            override fun onTabUnselected(p0: TabLayout.Tab?) {
//
//            }
//
//            override fun onTabSelected(tab: TabLayout.Tab) {
//                view_pager.currentItem = tab.position
//            }
//
//        })

        replace(R.id.detail_user_frame_layout, detailUserOverviewFragment)

        detail_user_bar.setOnMenuItemClickListener {
            when(it.itemId) {
                R.id.user_info -> replace(R.id.detail_user_frame_layout, detailUserOverviewFragment)

                R.id.user_repository -> replace(R.id.detail_user_frame_layout, detailUserRepositoryFragment)

                R.id.user_detail_followers -> replace(R.id.detail_user_frame_layout, detailUserFollowersFragment)

                R.id.user_detail_following -> replace(R.id.detail_user_frame_layout, detailUserFollowingFragment)
            }
            true
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.bottom_app_bar, menu)
        return super.onCreateOptionsMenu(menu)
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
