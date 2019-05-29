package tech.tsdev.github_management.view.main.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_issues.*
import org.jetbrains.anko.toast
import tech.tsdev.github_management.R

class IssuesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_issues)

        fab.setOnClickListener { finish() }

        bar.setNavigationOnClickListener {
            toast("준비중 입니다.")
        }
    }
}
