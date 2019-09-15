package com.example.marvels.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.marvels.model.Marvel
import com.example.marvels.repository.Repository
import com.example.marvels.model.Results

class MainActivityViewModel : ViewModel() {

    fun getCharacters(limit: Int, offset: Int): MutableLiveData<Marvel>? {
        return Repository.getCharacters(limit, offset)
    }

    fun searchInsideCharacters(result: ArrayList<Results>?, searchText: String): ArrayList<Results>? {
        var characters: ArrayList<Results>? = ArrayList()

        for (character in result!!) {
            if (character.name.trim().toLowerCase().startsWith(searchText.toLowerCase())) {
                characters?.add(character)
            }
        }
        return characters
    }
}