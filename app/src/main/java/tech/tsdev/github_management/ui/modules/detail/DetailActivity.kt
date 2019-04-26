package tech.tsdev.github_management.ui.modules.detail

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.toolbar_user_info.*
import tech.tsdev.github_management.R

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)



        println("username = ${intent.getStringExtra("userName")}")
        println("username = ${intent.getStringExtra("test")}")
        tv_user_name.text = intent.getStringExtra("userName")

        img_close_btn.setOnClickListener {
            finish()
        }
    }
}
