package tech.tsdev.github_management.view.main.activity


import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_my_follwers_user.*
import org.jetbrains.anko.toast
import tech.tsdev.github_management.R
import tech.tsdev.github_management.base.recycler.model.baseactivity.BaseActivity
import tech.tsdev.github_management.model.github.GithubRepository
import tech.tsdev.github_management.view.main.activity.adapter.followers.MyFollowersRecyclerAdapter
import tech.tsdev.github_management.view.main.activity.presenter.followers.MyFollowersContract
import tech.tsdev.github_management.view.main.activity.presenter.followers.MyFollowersPresenter

class MyFollowersUserActivity : BaseActivity(), MyFollowersContract.View {
    override fun dismissLottieProgressBar() {
        user_followers_recycler.visibility = View.VISIBLE
        lottie_progress_bar.visibility = View.GONE
    }

    override fun loadFailedMessage() {
        toast("로드 실패")
    }

    override fun loadFailedMessage(message: String) {
        toast(message)
    }


    private val myFollowersRecyclerAdapter: MyFollowersRecyclerAdapter by lazy {
        MyFollowersRecyclerAdapter(this)
    }

    private val myFollowersPresenter: MyFollowersPresenter by lazy {
        MyFollowersPresenter(
            this,
            GithubRepository,
            myFollowersRecyclerAdapter,
            disposable
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_follwers_user)

        println("userFollowersBasedUserName -> ${intent.getStringExtra("userFollowersBasedUserName")}")

        user_followers_recycler.run {
            adapter = myFollowersRecyclerAdapter
            layoutManager = GridLayoutManager(this@MyFollowersUserActivity, 1)
        }

        myFollowersPresenter.loadUserFollowersBasedUserName(intent.getStringExtra("userFollowersBasedUserName"))



        user_followers_name.text = intent.getStringExtra("userFollowersBasedUserName")
        img_followers_close.setOnClickListener { finish() }
    }
}
