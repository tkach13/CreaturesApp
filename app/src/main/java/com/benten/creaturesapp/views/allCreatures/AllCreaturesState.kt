package com.benten.creaturesapp.views.allCreatures

import com.benten.creaturesapp.model.Creature

sealed class AllCreaturesState {
        data class Success(val data: List<Creature>) : AllCreaturesState()
        data class Error(val error: Throwable) : AllCreaturesState()
}

enum class FilterRange(val range: IntRange) {
    HIT_POINTS_0_50(0..50),
    HIT_POINTS_50_100(50..100),
    HIT_POINTS_100_150(100..150)
}
