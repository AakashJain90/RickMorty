package com.assessment.rickmorty

import com.assessment.rickmorty.data.model.Character
import com.assessment.rickmorty.data.model.Location
import org.junit.Assert
import org.junit.Test

class CharacterTest {

    @Test
    fun testObject(){
        var character: Character? = null

        Assert.assertNull(character)

        character = Character(
            1, "Rick Sanchez", "Alive", "Human", "imageurl", Location("loc 1", "loc url")
        )

        Assert.assertNotNull(character)
    }

    @Test
    fun testAttributes(){

        val character = Character(
            1, "Rick Sanchez", "Alive", "Human", "imageurl", Location("loc 1", "loc url")
        )

        assert(character.id == 1)

        assert(character.name == "Rick Sanchez")

        assert(character.status == "Alive")

        assert(character.species == "Human")

        assert(character.location.name == "loc 1")
    }
}