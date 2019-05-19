package tech.tsdev.github_management.view.main.myfragment.viewpagerfragment

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fg_user_info_fragment_layout.*
import org.jetbrains.anko.support.v4.toast
import tech.tsdev.github_management.R
import tech.tsdev.github_management.model.github.GithubRepository
import tech.tsdev.github_management.view.main.activity.MyFollowersUserActivity
import tech.tsdev.github_management.view.main.myfragment.viewpagerfragment.presenter.MyInfoContract
import tech.tsdev.github_management.view.main.myfragment.viewpagerfragment.presenter.MyInfoPresenter

class MyInfoFragment : Fragment(), MyInfoContract.View {
    @SuppressLint("SetTextI18n")
    override fun getUserManyFollowerFollowing(userFollowers: Int?, userFollowings: Int?) {
        if (userFollowers?.minus(1) != 0 || userFollowings?.minus(1) != 0) {
            user_followers.text = "Followed by $userFollowers users"
            user_followings.text = "Following by $userFollowings users"
        } else {
            user_followers.text = "Followed by $userFollowers user"
            user_followings.text = "Following by $userFollowings user"
        }
    }

    override fun dismissLottieView() {
        user_lottie_view.visibility = View.GONE
        user_info_card_view.visibility = View.VISIBLE
        user_repo_card_view.visibility = View.VISIBLE
    }

    override fun getReposPreview(userName: String?) {
        userName?.let { user_repos.text = it } ?: let { user_repos.visibility = View.GONE }
    }

    override fun showGetSingleUserDetailInfo(
        userName: String?, userBio: String?,
        userEmail: String?,
        userWeb: String?
    ) {
        userName?.let { user_id.text = it } ?: let { user_id.visibility = View.GONE }
        userBio?.let { user_bio.text = it } ?: let { user_bio.visibility = View.GONE }
        userEmail?.let { user_email.text = it } ?: let { user_email.visibility = View.GONE }
        userWeb?.let { user_web.text = it } ?: let { user_web.visibility = View.GONE }
        if (userWeb.isNullOrBlank())
            user_web.visibility = View.GONE
    }

    override fun showLoadFailMessage() {
        toast("API 로드 실패")
    }

    override fun showLoadFailMessage(message: String) {
        toast(message)
    }


    private val myInfoPresenter: MyInfoPresenter by lazy {
        MyInfoPresenter(this@MyInfoFragment, GithubRepository)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fg_user_info_fragment_layout, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        println("MyInfoFragmentUserName -> ${arguments?.getString("userInfoName")}")

        myInfoPresenter.getUserInfoBasedOnUserName(arguments?.getString("userInfoName"))
        user_repos.setOnClickListener { println("repository Click") }

        user_followers.setOnClickListener {
            Intent(activity, MyFollowersUserActivity::class.java).apply {
                putExtra("userFollowersBasedUserName", arguments?.getString("userInfoName"))
                startActivity(this)
            }
        }

        user_followings.setOnClickListener { println("Followings Click") }
    }
}