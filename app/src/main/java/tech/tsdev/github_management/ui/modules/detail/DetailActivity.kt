package tech.tsdev.github_management.ui.modules.detail

import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import kotlinx.android.synthetic.main.activity_detail.*
import tech.tsdev.github_management.R
import tech.tsdev.github_management.ui.modules.detail.mine.followers.DetailUserFollowersFragment
import tech.tsdev.github_management.ui.modules.detail.mine.following.DetailUserFollowingFragment
import tech.tsdev.github_management.ui.modules.detail.mine.overview.DetailUserOverviewFragment
import tech.tsdev.github_management.ui.modules.detail.mine.repo.DetailUserRepoFragment
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


        replace(R.id.detail_user_frame_layout, detailUserOverviewFragment)

        detail_user_bar.setOnMenuItemClickListener {
            when (it.itemId) {
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
