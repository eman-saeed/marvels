package com.example.marvels.model

import androidx.paging.PagedList
import java.io.Serializable

data class Marvel(
    val code: Int,
    val status: String,
    val copyright: String,
    val attributionText: String,
    val attributionHTML: String,
    val etag: String,
    val data: Data
) : Serializable