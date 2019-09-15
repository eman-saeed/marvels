package com.example.marvels.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.example.marvels.R
import com.squareup.picasso.Picasso


class ImageUtil {

    companion object {
        fun setImageViewResource(imageView: ImageView, resourceURI: String) {
            Picasso.get()
                .load(convertUrlToHttps(resourceURI))
                .placeholder(R.mipmap.image_not_available)
                .error(R.mipmap.image_not_available)
                .into(imageView)

        }

        fun convertUrlToHttps(url: String): String {
            return url.replace("http://", "https://")
        }
    }
}