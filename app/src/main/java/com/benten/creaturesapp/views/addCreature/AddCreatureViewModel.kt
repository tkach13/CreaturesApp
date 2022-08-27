package com.benten.creaturesapp.views.addCreature

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.benten.creaturesapp.model.AttributeValue
import com.benten.creaturesapp.model.CreatureAttributes
import com.benten.creaturesapp.model.CreatureGenerator
import com.benten.creaturesapp.model.room.CreaturesRepositoryImpl

class AddCreatureViewModel : ViewModel() {
    private val creatureGenerator = CreatureGenerator()
    private val creaturesRepo = CreaturesRepositoryImpl()
    private val savedLiveData = MutableLiveData<Boolean>()

    fun getSavedLiveData(): LiveData<Boolean> {
        return savedLiveData
    }

    fun onSaveClicked(creatureAttributes: CreatureAttributes, name: String, chosenAvatar: Int) {
        val creature = creatureGenerator.generateCreature(creatureAttributes, name, chosenAvatar)
        creaturesRepo.insertCreature(creature)
        savedLiveData.postValue(true)
    }

    fun onAttributeSelected(attributeValue: AttributeValue) {

    }


}