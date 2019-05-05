package tech.tsdev.github_management.ui.modules.detail.search.searchactiviry

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.toolbar_search.*
import tech.tsdev.github_management.R
import tech.tsdev.github_management.ui.modules.detail.DetailActivity
import tech.tsdev.github_management.ui.modules.detail.search.repo.SearchRepoFragment
import tech.tsdev.github_management.ui.modules.detail.search.searchactiviry.presenter.SearchContract
import tech.tsdev.github_management.ui.modules.detail.search.users.SearchUserFragment

class SearchActivity : AppCompatActivity(), SearchContract.View {

    private var inputText = ""

    override fun showSuccessLayout() {
        linear_layout_github.visibility = View.GONE
        search_tab_layout.visibility = View.VISIBLE
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


    private fun addTabItems() {
        search_tab_layout.addTab(search_tab_layout.newTab().setText(R.string.repository))
        search_tab_layout.addTab(search_tab_layout.newTab().setText(R.string.users))
    }

    override fun onDestroy() {
        super.onDestroy()


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)


        addTabItems()

        search_tab_layout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(p0: TabLayout.Tab?) {

            }

            override fun onTabUnselected(p0: TabLayout.Tab?) {

            }

            override fun onTabSelected(tab: TabLayout.Tab) {
                pager.currentItem = tab.position
            }

        })

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



        btn_search.setOnClickListener {
            showSuccessLayout()
            inputText = et_search.text.toString()

            pager.run {
                adapter = SearchViewPagerAdapter(supportFragmentManager)
                addOnPageChangeListener(object : TabLayout.TabLayoutOnPageChangeListener(search_tab_layout) {})
            }
        }
        btn_clear.setOnClickListener {
            et_search.setText("")
        }

        btn_back.setOnClickListener {
            finish()
        }

    }

    inner class SearchViewPagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {
        override fun getItem(position: Int): Fragment? =
            when (position) {
                0 -> {
                    SearchRepoFragment().apply {
                        arguments = Bundle().apply {
                            putString("searchQuery", inputText)
                        }
                    }
                }
                1 -> {
                    SearchUserFragment().apply {
                        arguments = Bundle().apply {
                            putString("searchUserName", inputText)
                        }
                    }
                }
                else -> null
            }


        override fun getCount(): Int = 2

    }
}
