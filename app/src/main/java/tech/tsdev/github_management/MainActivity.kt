package tech.tsdev.github_management

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import tech.tsdev.github_management.util.replace
import tech.tsdev.github_management.view.main.myfragment.MyFragment
import tech.tsdev.github_management.view.main.userlistfragment.GithubFragment

class MainActivity : AppCompatActivity() {

    private val githubFragment : GithubFragment by lazy {
        GithubFragment()
    }

    private val myGithubFragment : MyFragment by lazy {
        MyFragment().apply {
            arguments = Bundle().apply {
                putInt(MyFragment.KEY_TITLE, R.string.github_me)
            }
        }
    }
    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when(item.itemId) {
            R.id.navigation_users -> {
                replace(R.id.frame_layout, githubFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_star -> {
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_me -> {
                replace(R.id.frame_layout, myGithubFragment)
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
    }
}
