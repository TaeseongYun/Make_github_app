package tech.tsdev.github_management.view.main.activity


import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_name_input.*
import tech.tsdev.github_management.R
import tech.tsdev.github_management.view.main.myfragment.MyFragment

class NameInputActivity : AppCompatActivity() {
    private val HELPER_TEXT = "*Require"
    private val ERROR_TEXT = "Empty_EditText"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_name_input)

        if(intent.getStringExtra("userName") != null) {
            finish()
        }
        text_input_layout.apply {
            helperText = HELPER_TEXT
        }

        btn_main_activity_start.setOnClickListener {

            if(text_input_edit_text.text.isNullOrEmpty()) {
                text_input_layout.error = ERROR_TEXT
            } else {
                val userName = text_input_edit_text.text.toString()
                intent.putExtra("userName", userName)
                Intent(this, MainActivity::class.java).apply {
                    putExtra("inputUserName", userName)
                    startActivity(this)
                }
                finish()
            }
        }

    }
}
