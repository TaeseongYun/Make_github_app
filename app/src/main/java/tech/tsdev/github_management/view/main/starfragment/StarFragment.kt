package tech.tsdev.github_management.view.main.starfragment

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.user_activities_fragment.*
import org.jetbrains.anko.support.v4.toast
import tech.tsdev.github_management.R
import tech.tsdev.github_management.model.github.GithubRepository
import tech.tsdev.github_management.view.main.activity.repos.DetailRepoActivity
import tech.tsdev.github_management.view.main.starfragment.adapter.StarRecyclerAdapter
import tech.tsdev.github_management.view.main.starfragment.presenter.StarFragmentContract
import tech.tsdev.github_management.view.main.starfragment.presenter.StarFragmentPresenter

class StarFragment : Fragment(), StarFragmentContract.View {
    override fun getDetailRepository(ownerName: String, repoName: String) {
        Intent(activity, DetailRepoActivity::class.java).apply {
            putExtra("ownerName", ownerName)
            putExtra("repoName", repoName)
            startActivity(this)
        }
    }

    override fun loadFailedMessage() {
        toast("로드 실패")
    }

    override fun loadFailMessage(message: String) {
        toast(message)
    }


    private val starRecyclerAdapter: StarRecyclerAdapter by lazy {
        StarRecyclerAdapter(this@StarFragment.context!!)
    }

    private val starFragmentPresenter: StarFragmentPresenter by lazy {
        StarFragmentPresenter(this@StarFragment, GithubRepository, starRecyclerAdapter)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.user_activities_fragment, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        println("star Fragment arguments -> ${arguments?.getString("userName")}")
        arguments?.getString("userName")?.let { starFragmentPresenter.getResultReceivedBasedOnUserName(it) }

        user_activities_recycler_view.run {
            adapter = starRecyclerAdapter
            layoutManager = GridLayoutManager(this@StarFragment.context, 1)
        }
    }
}
