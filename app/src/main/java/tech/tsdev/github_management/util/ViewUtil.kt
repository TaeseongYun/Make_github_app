package tech.tsdev.github_management.util

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import org.jetbrains.anko.imageResource
import tech.tsdev.github_management.R
import tech.tsdev.github_management.view.main.custom.GlideImageView

fun String?.ifNullGoneView(@IdRes viewContent: TextView?, @IdRes layoutContent: LinearLayout? = null) =
    this?.let { viewContent?.text = it } ?: let {
        viewContent?.text = ""
        layoutContent?.visibility = View.GONE
    }

fun String?.ifNullDefaultImg(@IdRes viewContent: GlideImageView) =
    this?.let { viewContent.proflieImageLoad(it) }
        ?: let { viewContent.imageResource = R.drawable.ic_launcher_foreground }

fun Fragment.getInstance(key: String = "", value: String = ""): Fragment =
    this.apply {
        arguments = Bundle().apply {
            putString(key, value)
        }
    }


// ViewPager 리스너 함수 생성
fun tabLayoutListener(viewPager: ViewPager) = object : TabLayout.OnTabSelectedListener {
    override fun onTabReselected(tab: TabLayout.Tab?) {

    }

    override fun onTabUnselected(tab: TabLayout.Tab?) {

    }

    override fun onTabSelected(tab: TabLayout.Tab) {
        viewPager.currentItem = tab.position
    }

}

inline fun <reified T : Activity> Context.getInstance(
    key: String = "", value: String? = "",
    extraKey: String = "", extraValue: String? = ""
) =
    Intent(this, T::class.java).apply {
        putExtra(key, value)
        if (extraKey.isNotEmpty())
            putExtra(extraKey, extraValue)
        startActivity(this)
    }