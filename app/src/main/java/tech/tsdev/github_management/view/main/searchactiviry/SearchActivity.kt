package tech.tsdev.github_management.view.main.searchactiviry

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import kotlinx.android.synthetic.main.activity_search.*
import tech.tsdev.github_management.R
import tech.tsdev.github_management.view.main.searchactiviry.presenter.SearchContract

class SearchActivity : AppCompatActivity(), SearchContract.View {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)


        et_search.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s.isNullOrEmpty())
                    btn_clear.visibility = View.GONE
                else
                    btn_clear.visibility = View.VISIBLE
            }

        })

        btn_clear.setOnClickListener {
            et_search.setText("")
        }

        btn_back.setOnClickListener {
            finish()
        }
    }
}
