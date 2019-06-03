package tech.tsdev.github_management.view.main.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_repo_stargazers.*
import tech.tsdev.github_management.R

class RepoStargazersActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repo_stargazers)

        repo_stargazers_back_img.setOnClickListener { finish() }
    }
}
