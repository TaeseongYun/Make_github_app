package tech.tsdev.github_management.view.main.myfragment



import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.app_bar_user_detail.*
import org.jetbrains.anko.support.v4.toast
import tech.tsdev.github_management.R
import tech.tsdev.github_management.base.recycler.model.basefragment.BaseFragment
import tech.tsdev.github_management.model.github.GithubRepository
import tech.tsdev.github_management.view.main.myfragment.viewpagerfragment.MyInfoFragment
import tech.tsdev.github_management.view.main.myfragment.viewpagerfragment.MyUserStarFragment
import tech.tsdev.github_management.view.main.myfragment.presenter.MyFragmentContract
import tech.tsdev.github_management.view.main.myfragment.presenter.MyFragmentPresenter

class MyFragment : BaseFragment(), MyFragmentContract.View {

    companion object {
        fun getInstance(key: String = "", value: String = "") =
            MyFragment().apply {
                arguments = Bundle().apply {
                    putString(key, value)
                }
            }
    }

    private  var userInfoName: String? = ""

    override fun showProgressBar() {
        loader_my_fragment?.visibility = View.VISIBLE
    }

    override fun dismissProgressBar() {
        loader_my_fragment?.visibility = View.GONE
    }

    override fun loadUserDetailInfo(
        userAvatar: String, userBackground: String, userLogin: String,
        userLocation: Any?, userJoinTime: String
    ) {
        user_avatar?.proflieImageLoad(userAvatar)
        user_avatar_bg?.getProfileImgRepo(userBackground)
        user_login?.text = userLogin
        userLocation?.let { user_location?.text = it.toString() } ?: let { user_location?.visibility = View.INVISIBLE }
        joined_time?.text = userJoinTime
    }

    override fun loadViewToastMessage() {
        toast("로드 실패").show()
    }

    override fun loadFailToastMessage(message: String) {
        toast(message).show()
    }


    private val myFragmentPresenter: MyFragmentPresenter by lazy {
        MyFragmentPresenter(this@MyFragment, GithubRepository, disposable)
    }
    private fun addTabLayoutItem() {
        user_info_tab_layout.addTab(user_info_tab_layout.newTab().setText(R.string.userInfo))
        user_info_tab_layout.addTab(user_info_tab_layout.newTab().setText(R.string.userStar))
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
            = inflater.inflate(R.layout.my_info_fragment, container, false)




    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        println("argument -> ${arguments?.getString("userName")}")

        myFragmentPresenter.inputUserNameLoad(arguments?.getString("userName"))

        userInfoName = arguments?.getString("userName")


        addTabLayoutItem()

        user_info_tab_layout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab) {
                viewPager.currentItem = tab.position
            }

            override fun onTabUnselected(p0: TabLayout.Tab?) {

            }

            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager.currentItem = tab.position
            }

        })
        viewPager.run {
            adapter = fragmentManager?.let { MyFragmentViewpageAdapter(it) }
            addOnPageChangeListener(object : TabLayout.TabLayoutOnPageChangeListener(user_info_tab_layout){})
        }
    }


    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        viewPager.run {
            adapter = fragmentManager?.let { MyFragmentViewpageAdapter(it) }
            addOnPageChangeListener(object : TabLayout.TabLayoutOnPageChangeListener(user_info_tab_layout){})
        }
    }
    inner class MyFragmentViewpageAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {
        override fun getItem(position: Int): Fragment? =
            when(position) {
                0 -> {
                    MyInfoFragment().apply {
                        arguments = Bundle().apply {
                            putString("userInfoName", userInfoName)
                        }
                    }
                }
                1 -> {
                    MyUserStarFragment().apply {
                        arguments = Bundle().apply {
                            putString("userInfoName", userInfoName)
                        }
                    }
                }
                else -> null
            }


        override fun getCount(): Int = 2
    }
}