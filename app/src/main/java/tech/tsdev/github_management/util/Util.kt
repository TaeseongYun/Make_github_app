package tech.tsdev.github_management.util

import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity

fun AppCompatActivity.replace(@IdRes frameId: Int, fragment: Fragment, tag: String? = null) {
    supportFragmentManager.beginTransaction().replace(frameId, fragment, tag).commit()
}