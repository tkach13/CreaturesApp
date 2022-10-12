package com.benten.creaturesapp.model

class CreatureGenerator {
    fun generateCreature(
        attributes: CreatureAttributes,
        name: String = "",
        drawable: Int = 0
    ): Creature {
        val hitPoints = INTELIGENCE_MULTIPLIER * attributes.intelligence +
                STRENGTH_MULTIPLIER * attributes.strength +
                ENDURANCE_MULTIPLIER * attributes.endurance
        return Creature(attributes, hitPoints, name, drawable)
    }

    companion object {
        const val STRENGTH_MULTIPLIER = 3
        const val INTELIGENCE_MULTIPLIER = 5
        const val ENDURANCE_MULTIPLIER = 4
    }
}