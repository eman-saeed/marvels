package com.example.marvels.model

import java.io.Serializable

data class Model(
    val available: Int,
    val collectionURI: String,
    val type: String,
    val items: ArrayList<Items>
) : Serializable