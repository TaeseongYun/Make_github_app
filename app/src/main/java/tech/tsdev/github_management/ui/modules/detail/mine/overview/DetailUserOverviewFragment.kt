package tech.tsdev.github_management.ui.modules.detail.mine.overview


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.detail_user_overview.*
import kotlinx.android.synthetic.main.fg_user_info_fragment_layout.*
import org.jetbrains.anko.support.v4.toast
import tech.tsdev.github_management.R
import tech.tsdev.github_management.base.recycler.model.basefragment.BaseFragment
import tech.tsdev.github_management.model.github.GithubRepository
import tech.tsdev.github_management.network.RetrofitObject
import tech.tsdev.github_management.ui.modules.detail.mine.overview.presenter.DetailUserOverviewContract
import tech.tsdev.github_management.ui.modules.detail.mine.overview.presenter.DetailUserOverviewPresenter
import tech.tsdev.github_management.util.ifNullDefaultImg
import tech.tsdev.github_management.util.ifNullGoneView
import tech.tsdev.github_management.view.main.activity.SearchActivity

class DetailUserOverviewFragment : BaseFragment(), DetailUserOverviewContract.View {

    companion object {
        fun getInstance(key: String = "", value: String = "") =
                DetailUserOverviewFragment().apply {
                    arguments = Bundle().apply {
                        putString(key, value)
                    }
                }
    }
    private val detailUserOverviewPresenter: DetailUserOverviewPresenter by lazy {
        DetailUserOverviewPresenter(
            this@DetailUserOverviewFragment,
            GithubRepository.getInstance(RetrofitObject.githubAPI),
            disposable
        )
    }

    override fun showFailMessage() {
        toast("로드 실패")
    }

    override fun showFailMessage(message: String) {
        toast(message)
    }

    override fun loadUserDetailView(
        userLogin: String?, userName: String?,
        userAvatar: String?, userEmail: String?, userDescription: String?,
        userFollowers: Int, userFollowings: Int, userRepoCount: Int,
        userLocation: String?
    ) {
        userEmail.ifNullGoneView(detail_user_email, user_email_layout)
        userAvatar?.ifNullDefaultImg(detail_user_img)
        detail_user_login_name?.text = userLogin
        userDescription?.ifNullGoneView(detail_user_description)
        user_followers_many?.text = userFollowers.toString()
        user_followings_many?.text = userFollowings.toString()
        user_repo_many?.text = userRepoCount.toString()
        userLocation?.ifNullGoneView(detail_user_location, detail_user_location_layout)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        println("get String value1 -> ${arguments?.getString("fragmentUserName")}")
        println("get String value3 -> ${arguments?.getString("fragmentUserName")}")
        return inflater.inflate(R.layout.detail_user_overview, container, false)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.getString(SearchActivity.USER_NAME)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        println("get String 값2 -> ${arguments?.getString("fragmentUserName")}")
        arguments?.getString("fragmentUserName")?.let { detailUserOverviewPresenter.loadUserOverView(it) }
    }
}