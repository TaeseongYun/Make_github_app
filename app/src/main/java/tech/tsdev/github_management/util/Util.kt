package tech.tsdev.github_management.util


import android.view.View
import android.widget.TextView
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

fun AppCompatActivity.replace(@IdRes frameId: Int, fragment: Fragment, tag: String? = null) {
    supportFragmentManager.beginTransaction().replace(frameId, fragment, tag).commit()
}

fun TextView.textVisibleGone() {
    this.visibility = View.GONE
}