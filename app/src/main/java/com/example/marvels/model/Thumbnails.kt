package com.example.marvels.model

import java.io.Serializable

data class Thumbnails(
    val path: String,
    val extension: String
) : Serializable {
    fun getImageURI(): String {
        return path.plus("/landscape_xlarge.").plus(extension)
    }
}