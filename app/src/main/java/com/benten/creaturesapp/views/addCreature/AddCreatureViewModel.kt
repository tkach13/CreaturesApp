package com.benten.creaturesapp.views.addCreature

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.benten.creaturesapp.di.DataModule
import com.benten.creaturesapp.model.AttributeValue
import com.benten.creaturesapp.model.CreatureAttributes
import com.benten.creaturesapp.model.CreatureGenerator
import com.benten.creaturesapp.model.CreaturesRepository
import com.benten.creaturesapp.model.room.CreaturesRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddCreatureViewModel @Inject constructor(
    private val creaturesGenerator: CreatureGenerator,
    @DataModule.RealRepo private val creaturesRepo: CreaturesRepository
) :
    ViewModel() {


    private val savedLiveData = MutableLiveData<Boolean>()

    fun getSavedLiveData(): LiveData<Boolean> {
        return savedLiveData
    }

    fun onSaveClicked(creatureAttributes: CreatureAttributes, name: String, chosenAvatar: Int) {
        val creature = creaturesGenerator.generateCreature(creatureAttributes, name, chosenAvatar)
        creaturesRepo.insertCreature(creature)
        savedLiveData.postValue(true)
    }

    fun onAttributeSelected(attributeValue: AttributeValue) {

    }


}