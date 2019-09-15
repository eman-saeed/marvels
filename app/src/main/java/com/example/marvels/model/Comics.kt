package com.example.marvels.model

data class Comics(
    val available: Int,
    val collectionURI: String,
    val items: Items,
    val returned: Int
)