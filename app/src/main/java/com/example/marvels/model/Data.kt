package com.example.marvels.model

import java.io.Serializable

data class Data(
    val offest: Int,
    val limit: Int,
    val total: Int,
    val count: Int,
    val results: ArrayList<Results>
):Serializable