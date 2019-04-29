package tech.tsdev.github_management.ui.modules.detail

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.toolbar_user_info.*
import tech.tsdev.github_management.R
import tech.tsdev.github_management.ui.modules.detail.presenter.DetailContract

class DetailActivity : AppCompatActivity(), DetailContract.View {
    override fun addTablayoutItem() {
        tab_layout.addTab(tab_layout.newTab().setText(R.string.overview))
        tab_layout.addTab(tab_layout.newTab().setText(R.string.repository))
        tab_layout.addTab(tab_layout.newTab().setText(R.string.followers))
        tab_layout.addTab(tab_layout.newTab().setText(R.string.following))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)



//        println("username = ${intent.getStringExtra("userName")}")
//        println("username = ${intent.getStringExtra("test")}")
        tv_user_name.text = intent.getStringExtra("userName")

        addTablayoutItem()

        img_close_btn.setOnClickListener {
            finish()
        }
    }
}
