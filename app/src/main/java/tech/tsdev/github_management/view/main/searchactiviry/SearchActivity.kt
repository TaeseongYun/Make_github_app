package tech.tsdev.github_management.view.main.searchactiviry

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.toolbar_search.*
import tech.tsdev.github_management.R
import tech.tsdev.github_management.model.github.GithubRepository
import tech.tsdev.github_management.ui.modules.detail.DetailActivity
import tech.tsdev.github_management.view.main.searchactiviry.adapter.SearchRecyclerAdapter
import tech.tsdev.github_management.view.main.searchactiviry.presenter.SearchContract
import tech.tsdev.github_management.view.main.searchactiviry.presenter.SearchPresenter

class SearchActivity : AppCompatActivity(), SearchContract.View {
    override fun showSuccessLayout() {
        linear_layout_github.visibility = View.GONE
        search_recycler_view.visibility = View.VISIBLE
        error_layout.visibility = View.GONE
    }

    override fun showFailLayout() {
        error_layout.visibility = View.VISIBLE
        search_recycler_view.visibility = View.GONE
        linear_layout_github.visibility = View.GONE
    }

    companion object {
        val USER_NAME = "userName"

        fun getIntent(context: Context, userName: String): Intent {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(USER_NAME, userName)
            return intent
        }
    }
    override fun loadDetailActivity(userName: String) {

        startActivity(getIntent(this, userName))

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
