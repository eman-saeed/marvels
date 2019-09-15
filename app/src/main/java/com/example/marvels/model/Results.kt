package com.example.marvels.model

import java.io.FileDescriptor
import java.io.Serializable

data class Results(
    val id: Int,
    val name: String,
    val title: String,
    val description: String,
    val modified: String,
    val thumbnail: Thumbnails,
    val resourceURI: String,
    val comics: Model,
    val series: Model,
    val events: Model,
    val stories: Model,
    val urls: ArrayList<URLs>
) : Serializable
