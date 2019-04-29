package tech.tsdev.github_management.view.main.searchactiviry

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_search.*
import tech.tsdev.github_management.R
import tech.tsdev.github_management.model.github.GithubRepository
import tech.tsdev.github_management.ui.modules.detail.DetailActivity
import tech.tsdev.github_management.ui.modules.detail.overview.DetailUserOverviewFragment
import tech.tsdev.github_management.view.main.searchactiviry.adapter.SearchRecyclerAdapter
import tech.tsdev.github_management.view.main.searchactiviry.presenter.SearchContract
import tech.tsdev.github_management.view.main.searchactiviry.presenter.SearchPresenter

class SearchActivity : AppCompatActivity(), SearchContract.View {

    companion object {
        const val USER_NAME = "userName"
    }
    override fun loadDetailActivity(userName: String) {

        Intent(this, DetailActivity::class.java).apply {
            putExtra(USER_NAME, userName)
            startActivity(this)
        }
    }

    override fun loadErrorMessage() {
        Toast.makeText(this, "로드 실패", Toast.LENGTH_SHORT).show()
    }

    override fun loadErrorMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private val searchRecyclerAdapter: SearchRecyclerAdapter by lazy {
        SearchRecyclerAdapter(this)
    }
    private val searchPresenter: SearchPresenter by lazy {
        SearchPresenter(
            this@SearchActivity,
            GithubRepository,
            searchRecyclerAdapter
        )
    }

    private val recyclerViewonScrollManager = object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)

            val firstViewItem = (recyclerView.layoutManager as GridLayoutManager).findFirstVisibleItemPosition()
            val totalItemCount = recyclerView.childCount
            val visibleItem = recyclerView.childCount


        }
    }

    override fun onDestroy() {
        super.onDestroy()

        search_recycler_view.removeOnScrollListener(recyclerViewonScrollManager)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)


        et_search.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s.isNullOrEmpty()) {
                    btn_clear.visibility = View.GONE
                    btn_back.visibility = View.VISIBLE

                } else {
                    btn_clear.visibility = View.VISIBLE
                    btn_back.visibility = View.GONE
                }

            }

        })


        search_recycler_view.run {
            layoutManager = GridLayoutManager(this@SearchActivity, 1)
            adapter = searchRecyclerAdapter
            addOnScrollListener(recyclerViewonScrollManager)
        }
        btn_search.setOnClickListener {
            linear_layout_github.visibility = View.GONE
            search_recycler_view.visibility = View.VISIBLE

            searchRecyclerAdapter.clearItem()
            searchPresenter.searchLoadUsers(et_search.text.toString())
        }
        btn_clear.setOnClickListener {
            et_search.setText("")
        }

        btn_back.setOnClickListener {
            finish()
        }

    }
}
