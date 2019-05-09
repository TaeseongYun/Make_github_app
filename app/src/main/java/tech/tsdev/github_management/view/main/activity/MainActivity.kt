package tech.tsdev.github_management.view.main.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.view.View
import kotlinx.android.synthetic.main.main_bottom_navigation.*
import kotlinx.android.synthetic.main.toolbar_main.*
import tech.tsdev.github_management.R
import tech.tsdev.github_management.util.replace
import tech.tsdev.github_management.view.main.myfragment.MyFragment
import tech.tsdev.github_management.ui.modules.detail.search.searchactiviry.SearchActivity
import tech.tsdev.github_management.view.main.starfragment.StarFragment
import tech.tsdev.github_management.view.main.userlistfragment.GithubFragment

class MainActivity : AppCompatActivity() {

    private val githubFragment : GithubFragment by lazy {
        GithubFragment()
    }

    private val myGithubFragment : MyFragment by lazy {
        MyFragment().apply {
            arguments = Bundle().apply {
                putString("userName", intent.getStringExtra("inputUserName"))
            }
        }
    }

    private val starFragment: StarFragment by lazy {
        StarFragment()
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when(item.itemId) {
            R.id.navigation_users -> {
                replace(R.id.frame_layout, githubFragment)
                app_bar.visibility = View.VISIBLE
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_star -> {
                replace(R.id.frame_layout, starFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_me -> {
                replace(R.id.frame_layout, myGithubFragment)
                app_bar.visibility = View.GONE
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        replace(R.id.frame_layout, githubFragment)
        navigation.apply {
            setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        }

        user_search_img.setOnClickListener {
            startActivity(Intent(this, SearchActivity::class.java))
        }
    }
}
