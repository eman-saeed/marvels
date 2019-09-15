package com.example.marvels.paging

import androidx.paging.PageKeyedDataSource
import com.example.marvels.model.Marvel
import com.example.marvels.repository.Repository

class CharactersDataSource() : PageKeyedDataSource<Int, Marvel>() {

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Marvel>) {
        var limit: Int = params.requestedLoadSize
        Repository.getCharacters(0, limit)
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Marvel>) {
        var limit = params.requestedLoadSize;
        var offset: Int = params.key * limit;
        Repository.getCharacters(0, limit)
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Marvel>) {
    }
}