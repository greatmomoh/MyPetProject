package com.example.mypetproject

import android.app.Application
import android.graphics.drawable.ColorDrawable
import androidx.core.content.ContextCompat
import coil.ImageLoader
import coil.ImageLoaderFactory
import coil.decode.SvgDecoder
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber


@HiltAndroidApp
class ApplicationClass : Application(), ImageLoaderFactory {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }

    override fun newImageLoader(): ImageLoader {
        return ImageLoader.Builder(this)
            .placeholder(ColorDrawable(ContextCompat.getColor(this, R.color.search_grey_bg)))
            .error(ColorDrawable(ContextCompat.getColor(this, R.color.search_grey_bg)))
            .crossfade(true)
            .componentRegistry {
                add(SvgDecoder(this@ApplicationClass))
            }
            .build()
    }


}