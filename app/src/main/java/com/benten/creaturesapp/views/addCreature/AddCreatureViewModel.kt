package com.benten.creaturesapp.views.addCreature

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.benten.creaturesapp.model.*

import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddCreatureViewModel @Inject constructor(
    private val creaturesGenerator: CreatureGenerator,
) :
    ViewModel() {


    private val savedLiveData = MutableLiveData<Boolean>()

    fun getSavedLiveData(): LiveData<Boolean> {
        return savedLiveData
    }

    fun onSaveClicked(creatureAttributes: CreatureAttributes, name: String, chosenAvatar: Int) {
        val creature = creaturesGenerator.generateCreature(creatureAttributes, name, chosenAvatar)
        savedLiveData.postValue(true)
    }

    fun onAttributeSelected(attributeValue: AttributeValue) {
    }


}