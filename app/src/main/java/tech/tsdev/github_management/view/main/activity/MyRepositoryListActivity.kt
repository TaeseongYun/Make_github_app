package tech.tsdev.github_management.view.main.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_my_repository_list.*
import tech.tsdev.github_management.R

class MyRepositoryListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_repository_list)

        user_repo_close_btn.setOnClickListener { finish() }

        tv_repo_list_owner_name.text = intent.getStringExtra("userRepoListBasedOnUserName")
    }
}
