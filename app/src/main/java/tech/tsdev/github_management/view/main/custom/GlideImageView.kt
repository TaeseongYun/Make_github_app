package tech.tsdev.github_management.view.main.custom

import android.content.Context
import android.support.annotation.DrawableRes
import android.util.AttributeSet
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import tech.tsdev.github_management.R

class GlideImageView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0)
    : ImageView( context, attrs, defStyleAttr ) {

    fun proflieImageLoad(url: String?, @DrawableRes loadingImgRes: Int = R.drawable.bg_gradation) {
        Glide.with(this)
            .load(url)
            .placeholder(loadingImgRes)
            .apply( RequestOptions.circleCropTransform())
            .into(this)
    }

    fun getProfileImgRepo(url: String, @DrawableRes loadingImgRes: Int = R.drawable.bg_gradation) {
        Glide.with(this)
            .load(url)
            .placeholder(loadingImgRes)
            .apply( RequestOptions.centerCropTransform() )
            .into(this)
    }
}