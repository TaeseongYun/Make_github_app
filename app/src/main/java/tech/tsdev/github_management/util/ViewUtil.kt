package tech.tsdev.github_management.util

import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.IdRes
import org.jetbrains.anko.imageResource
import tech.tsdev.github_management.R
import tech.tsdev.github_management.view.main.custom.GlideImageView

fun String?.ifNullGoneView(@IdRes viewContent: TextView?, @IdRes layoutContent: LinearLayout? = null) =
    this?.let { viewContent?.text = it } ?: let { viewContent?.text = ""
        layoutContent?.visibility = View.GONE }

fun String?.ifNullDefaultImg(@IdRes viewContent: GlideImageView) =
        this?.let { viewContent.proflieImageLoad(it) }
            ?: let { viewContent.imageResource = R.drawable.ic_launcher_foreground }