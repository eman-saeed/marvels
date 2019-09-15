package com.example.marvels.util

import android.view.View
import androidx.databinding.BindingAdapter

class TextUtil {

    companion object {
        fun isEmpty(str: String): Boolean {
            return str.isEmpty()
        }

        @set:BindingAdapter("isVisible")
        inline var View.isVisible: Boolean
            get() = visibility == View.VISIBLE
            set(value) {
                visibility = if (value) View.VISIBLE else View.GONE
            }
    }
}