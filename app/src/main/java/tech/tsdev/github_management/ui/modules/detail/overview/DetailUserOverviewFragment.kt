package tech.tsdev.github_management.ui.modules.detail.overview

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.detail_user_overview.*
import org.jetbrains.anko.support.v4.toast
import tech.tsdev.github_management.R
import tech.tsdev.github_management.model.github.GithubRepository
import tech.tsdev.github_management.ui.modules.detail.overview.presenter.DetailUserOverviewContract
import tech.tsdev.github_management.ui.modules.detail.overview.presenter.DetailUserOverviewPresenter

class DetailUserOverviewFragment : Fragment(), DetailUserOverviewContract.View {

    private val detailUserOverviewPresneter: DetailUserOverviewPresenter by lazy {
        DetailUserOverviewPresenter(
            this@DetailUserOverviewFragment,
            GithubRepository
        )
    }

    override fun showFailMessage() {
        toast("로드 실패")
    }

    override fun showFailMessage(message: String) {
        toast(message)
    }

    override fun loadUserDetailView(userLogin: String, userName: String, userAvatar: String, userEmail: String) {
        detail_user_email.text = userEmail
        detail_user_img.proflieImageLoad(userAvatar)
        detail_user_name.text = userName
        detail_user_login_name.text = userLogin
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        println("get String value1 -> ${arguments?.getString("fragmentUserName")}")
        return inflater.inflate(R.layout.detail_user_overview, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        println("get String 값2 -> ${arguments?.getString("fragmentUserName") }")
//        detailUserOverviewPresneter.loadUserOverView(arguments?.getString("fragmentUserName")!!)
    }
}