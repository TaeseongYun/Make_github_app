package tech.tsdev.github_management.view.main.activity


import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_name_input.*
import tech.tsdev.github_management.R

class NameInputActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_name_input)

        text_input_layout.apply {
            helperText = "*Require"
        }
    }
}
