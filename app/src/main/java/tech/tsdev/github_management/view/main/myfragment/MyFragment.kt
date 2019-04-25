package tech.tsdev.github_management.view.main.myfragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GithubAuthProvider
import kotlinx.android.synthetic.main.my_info_fragment.*
import tech.tsdev.github_management.BuildConfig
import tech.tsdev.github_management.R
import tech.tsdev.github_management.view.main.myfragment.presenter.MyFragmentContract
import tech.tsdev.github_management.view.main.myfragment.presenter.MyFragmentPresenter

class MyFragment : Fragment(), MyFragmentContract.View {

    private lateinit var auth: FirebaseAuth


    override fun loadViewToastMessage() {
        Toast.makeText(this.context, "로그인 성공", Toast.LENGTH_SHORT).show()
    }

    override fun loadFailToastMessage() {
        Toast.makeText(this.context, "로그인 실패", Toast.LENGTH_SHORT).show()
    }

    companion object {
        val KEY_TITLE = "key-title"
        const val GITHUB_TOKEN = BuildConfig.GITHUB_TOKEN
    }




    private val myFragmentPresenter: MyFragmentPresenter by lazy {
        MyFragmentPresenter(this@MyFragment)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.my_info_fragment, container, false).apply {
            auth = FirebaseAuth.getInstance()
        }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        textView.setText(arguments?.getInt(KEY_TITLE) ?: 0)

        btn_git_sign_in.setOnClickListener {
            myFragmentPresenter.githubLoginWithCridential(auth, GithubAuthProvider.getCredential(GITHUB_TOKEN))
        }

        btn_git_sign_out.setOnClickListener {
            auth.signOut()
        }

    }

    override fun onStart() {
        super.onStart()

        auth.addAuthStateListener {  }
    }

}