package tech.tsdev.github_management.ui.modules.detail

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.toolbar_user_info.*
import tech.tsdev.github_management.R
import tech.tsdev.github_management.ui.modules.detail.adapter.DetailViewPagerAdapter
import tech.tsdev.github_management.ui.modules.detail.overview.presenter.DetailUserOverviewContract

class DetailActivity : AppCompatActivity(), DetailUserOverviewContract.View {

    private val viewAdapter: DetailViewPagerAdapter by lazy {
        DetailViewPagerAdapter(supportFragmentManager)
    }
    override fun showFailMessage() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showFailMessage(message: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun loadUserDetailView() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun addTablayoutItem() {
        tab_layout.addTab(tab_layout.newTab().setText(R.string.overview))
        tab_layout.addTab(tab_layout.newTab().setText(R.string.repository))
        tab_layout.addTab(tab_layout.newTab().setText(R.string.followers))
        tab_layout.addTab(tab_layout.newTab().setText(R.string.following))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)


        tv_user_name.text = intent.getStringExtra("userName")

        addTablayoutItem()

        img_close_btn.setOnClickListener {
            finish()
        }


        // 뷰페이저에서 어뎁터 붙혀주고 addOnPageChangeListener 는 페이지가 변경 될때 Tab도 같이 변하게 묶어주는 리스너
        view_pager.run {
            adapter = viewAdapter
            addOnPageChangeListener(object : TabLayout.TabLayoutOnPageChangeListener(tab_layout){})
        }

        //탭 레이아웃 클릭 할때 해당하는 포지션의 fragment를 보여줌 (즉 바인딩 해줌)
        tab_layout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabReselected(p0: TabLayout.Tab?) {

            }

            override fun onTabUnselected(p0: TabLayout.Tab?) {

            }

            override fun onTabSelected(tab: TabLayout.Tab) {
                view_pager.currentItem = tab.position
            }

        })
    }
}
