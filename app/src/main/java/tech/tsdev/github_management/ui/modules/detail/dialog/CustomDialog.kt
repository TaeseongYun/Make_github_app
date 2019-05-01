package tech.tsdev.github_management.ui.modules.detail.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.WindowManager
import kotlinx.android.synthetic.main.bg_custom_dialog.*
import tech.tsdev.github_management.R

class CustomDialog(context: Context) : Dialog(context) {

    private lateinit var inputUserName: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val lpWindow = WindowManager.LayoutParams()
        lpWindow.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND
        lpWindow.dimAmount = 0.8f

        window.attributes = lpWindow

        setContentView(R.layout.bg_custom_dialog)

        btn_cancel.setOnClickListener {
            dismiss()
        }

        btn_submit.setOnClickListener { inputUserName = et_input_user_name.text.toString() }
    }
}