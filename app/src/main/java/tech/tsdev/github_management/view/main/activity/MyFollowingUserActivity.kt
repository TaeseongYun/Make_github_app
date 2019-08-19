package tech.tsdev.github_management.view.main.activity


import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_my_following_user.*
import org.jetbrains.anko.toast
import tech.tsdev.github_management.R
import tech.tsdev.github_management.base.recycler.model.baseactivity.BaseActivity
import tech.tsdev.github_management.model.github.GithubRepository
import tech.tsdev.github_management.view.main.activity.adapter.following.MyFollowingRecyclerAdapter
import tech.tsdev.github_management.view.main.activity.presenter.following.MyFollowingContract
import tech.tsdev.github_management.view.main.activity.presenter.following.MyFollowingPresenter

class MyFollowingUserActivity : BaseActivity(), MyFollowingContract.View {
    override fun dismissLottieProgressbar() {
        user_following_view.visibility = View.VISIBLE
        following_lottie_progress_bar.visibility = View.GONE
    }

    override fun loadFailedAnkoMessage() {
        toast("API 로드 실패")
    }

    override fun loadFailedAnkoMessage(message: String) {
        toast(message)
    }

    private val myFollowingRecyclerAdapter: MyFollowingRecyclerAdapter by lazy {
        MyFollowingRecyclerAdapter(this)
    }

    private val myFollowingPresenter: MyFollowingPresenter by lazy {
        MyFollowingPresenter(this, GithubRepository, myFollowingRecyclerAdapter, disposable)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_following_user)

        myFollowingPresenter.getFollowingUserListBasedUserName(intent.getStringExtra("userFollowingBasedUserName"))

        user_following_toolbar_name.text = intent.getStringExtra("userFollowingBasedUserName")

        user_following_view.run {
            adapter = myFollowingRecyclerAdapter
            layoutManager = GridLayoutManager(this.context, 1)
        }

        println("들어온 intentGetString 값 -> ${intent.getStringExtra("userFollowingBasedUserName")}")

        img_close_user_following.setOnClickListener { finish() }
    }
}
