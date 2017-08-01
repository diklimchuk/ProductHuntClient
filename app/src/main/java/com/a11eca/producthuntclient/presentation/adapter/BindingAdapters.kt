package com.a11eca.producthuntclient.presentation.adapter

import android.databinding.BindingAdapter
import android.widget.ImageView
import com.a11eca.producthuntclient.R
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget


@BindingAdapter("bind:imageUrl")
fun loadImage(view: ImageView, url: String?) {
  if (url != null) {
    val imageViewTarget = GlideDrawableImageViewTarget(view)
    Glide.with(view.context)
        .load(url)
        .error(R.color.colorAccent)
        .into(imageViewTarget)
  }
}