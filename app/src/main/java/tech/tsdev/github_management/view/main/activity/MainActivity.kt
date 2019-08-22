package tech.tsdev.github_management.view.main.activity


import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable

import android.os.Bundle


import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.yalantis.contextmenu.lib.ContextMenuDialogFragment
import com.yalantis.contextmenu.lib.MenuGravity
import com.yalantis.contextmenu.lib.MenuObject
import com.yalantis.contextmenu.lib.MenuParams
import kotlinx.android.synthetic.main.main_bottom_navigation.*
import kotlinx.android.synthetic.main.toolbar_main.*
import tech.tsdev.github_management.R
import tech.tsdev.github_management.util.replace
import tech.tsdev.github_management.view.main.myfragment.MyFragment
import tech.tsdev.github_management.view.main.starfragment.StarFragment
import tech.tsdev.github_management.view.main.userlistfragment.GithubFragment

class MainActivity : AppCompatActivity() {

    private val githubFragment: GithubFragment by lazy {
        GithubFragment()
    }

    private val myGithubFragment: Fragment by lazy {
        MyFragment.getInstance("userName", intent.getStringExtra("inputUserName"))
    }

    private val starFragment: StarFragment by lazy {
        StarFragment.getInstance("userName", intent.getStringExtra("inputUserName"))
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_users -> {

                replace(R.id.frame_layout, githubFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_star -> {
                replace(R.id.frame_layout, starFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_me -> {
                replace(R.id.frame_layout, myGithubFragment)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.change_user_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        item?.let {
            when (it.itemId) {
                R.id.change_user -> {
                    finish()
                    startActivity(Intent(this, NameInputActivity::class.java))
                }
                else -> {
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(custom_toolbar)

        replace(R.id.frame_layout, githubFragment)

        navigation.apply {
            setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        }

        user_search_img.setOnClickListener {
            startActivity(Intent(this, SearchActivity::class.java))
        }

        val bitmapDrawable = BitmapDrawable(
            resources,
            BitmapFactory.decodeResource(resources, R.drawable.ic_keyboard_arrow_down_black_24dp)
        )
        val close = MenuObject().apply {
            drawable = bitmapDrawable
            menuTextAppearanceStyle = R.style.TextViewStyle
        }
        val menuObject = mutableListOf<MenuObject>().apply {
            add(close)
        }

        ContextMenuDialogFragment.newInstance(
            MenuParams(
                resources.getDimension(R.dimen.text_large).toInt(),
                menuObjects = menuObject,
                isClosableOutside = false,
                gravity = MenuGravity.END
            )
        )
    }
}
