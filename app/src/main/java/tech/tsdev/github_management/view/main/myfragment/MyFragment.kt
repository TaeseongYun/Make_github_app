package tech.tsdev.github_management.view.main.myfragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.app_bar_user_detail.*
import kotlinx.android.synthetic.main.my_info_fragment.*
import org.jetbrains.anko.support.v4.toast
import tech.tsdev.github_management.R
import tech.tsdev.github_management.model.github.GithubRepository
import tech.tsdev.github_management.ui.modules.detail.user.dialog.CustomDialog
import tech.tsdev.github_management.view.main.myfragment.presenter.MyFragmentContract
import tech.tsdev.github_management.view.main.myfragment.presenter.MyFragmentPresenter

class MyFragment : Fragment(), MyFragmentContract.View {
    override fun loadUserDetailInfo(
        userAvatar: String, userLogin: String,
        userLocation: Any?, userJoinTime: String
    ) {
        user_avatar.proflieImageLoad(userAvatar)
        user_login.text = userLogin
        userLocation?.let { user_location.text = it.toString() } ?: let { user_location.visibility = View.INVISIBLE }
        joined_time.text = userJoinTime
    }

    override fun loadViewToastMessage() {
        toast("로드 실패").show()
    }

    override fun loadFailToastMessage(message: String) {
        toast(message).show()
    }


    private val myFragmentPresenter: MyFragmentPresenter by lazy {
        MyFragmentPresenter(this@MyFragment, GithubRepository)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.my_info_fragment, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        println("argument -> ${arguments?.getString("userName")}")

        myFragmentPresenter.inputUserNameLoad(arguments?.getString("userName"))

    }

    override fun onResume() {
        super.onResume()

        Log.d("onResume", "Resume")
    }

    override fun onStop() {
        super.onStop()

        Log.d("onStop", "Stop")
    }

    override fun onDetach() {
        super.onDetach()
        Log.d("onDetach", "Detach")
    }

    override fun onPause() {
        super.onPause()

        Log.d("onPause", "Pause")
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)

        Log.d("onRestart", "Restart")
    }
}