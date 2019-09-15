package com.example.marvels.paging

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.marvels.model.Marvel

class CharactersDataSourceFactory() : DataSource.Factory<Int, Marvel>() {

    override fun create(): DataSource<Int, Marvel> {
        return CharactersDataSource()
    }
}