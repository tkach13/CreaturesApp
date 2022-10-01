package com.benten.creaturesapp.views.addCreature.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class AddCreatureDataModel(
    val image: Int,
    val name: String,
    val points: Int
) : Parcelable

