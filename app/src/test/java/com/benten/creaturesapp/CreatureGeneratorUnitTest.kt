package com.benten.creaturesapp

import com.benten.creaturesapp.model.CreatureAttributes
import com.benten.creaturesapp.model.CreatureGenerator
import com.google.common.truth.Truth.assertThat
import org.junit.Test


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class CreatureGeneratorUnitTest {

    private val creatureGenerator = CreatureGenerator()

    @Test
    fun `generate_creature_works_correctly`() {
        val hitPoints = creatureGenerator.generateCreature(CreatureAttributes(2, 3, 4)).hitPoints
        assertThat(hitPoints).isEqualTo(35)
    }
}