package com.ari.movies.ui.bindingadapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load
import com.ari.movies.R

@BindingAdapter("poster")
fun loadPoster(iv: ImageView, posterUrl: String?) {
    if (posterUrl == null) {
        iv.setImageResource(R.drawable.ic_baseline_image_24)
        return
    }

    iv.load("https://image.tmdb.org/t/p/w500$posterUrl") {
        crossfade(true)
        placeholder(R.drawable.ic_baseline_image_24)
    }
}