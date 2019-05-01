package tech.tsdev.github_management.view.main.myfragment

import android.app.AlertDialog
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.my_info_fragment.*
import tech.tsdev.github_management.BuildConfig
import tech.tsdev.github_management.R
import tech.tsdev.github_management.ui.modules.detail.dialog.CustomDialog
import tech.tsdev.github_management.view.main.myfragment.presenter.MyFragmentContract
import tech.tsdev.github_management.view.main.myfragment.presenter.MyFragmentPresenter

class MyFragment : Fragment(), MyFragmentContract.View {




    override fun loadViewToastMessage() {
        Toast.makeText(this.context, "로그인 성공", Toast.LENGTH_SHORT).show()
    }

    override fun loadFailToastMessage() {
        Toast.makeText(this.context, "로그인 실패", Toast.LENGTH_SHORT).show()
    }

    companion object {
        val KEY_TITLE = "key-title"
        const val GITHUB_TOKEN = BuildConfig.GITHUB_TOKEN
    }




    private val myFragmentPresenter: MyFragmentPresenter by lazy {
        MyFragmentPresenter(this@MyFragment)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.my_info_fragment, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        name_input.setOnClickListener { CustomDialog(this@MyFragment.context!!).show() }
    }
}