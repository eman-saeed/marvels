package com.example.marvels.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.marvels.model.Marvel
import com.example.marvels.repository.Repository

class CharacterDetailsViewModel : ViewModel() {

    fun getDetails(characterId: Int, serviceName: String): MutableLiveData<Marvel>? {
        return Repository.getDetails(characterId, serviceName)
    }
}