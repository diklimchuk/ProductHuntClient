package com.a11eca.producthuntclient.presentation.adapter

import android.databinding.BindingAdapter
import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget


@BindingAdapter("bind:imageUrl", "@bind:error")
fun loadImage(view: ImageView, url: String?, error: Drawable) {
  val imageViewTarget = GlideDrawableImageViewTarget(view)
  Glide.with(view.context).load(url ?: "").error(error).into(imageViewTarget)
}