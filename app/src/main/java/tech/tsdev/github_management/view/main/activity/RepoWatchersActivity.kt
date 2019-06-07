package tech.tsdev.github_management.view.main.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_repo_watchers.*
import tech.tsdev.github_management.R

class RepoWatchersActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repo_watchers)

        repo_watcher_close_btn.setOnClickListener { finish() }
        repo_watcher_name.text = intent.getStringExtra("repoName")
    }
}
