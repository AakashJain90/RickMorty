package com.assessment.rickmorty.utils

import com.assessment.rickmorty.data.model.Character
import com.assessment.rickmorty.data.model.Location

object FakeCharacters {

    val fakeCharacterRick = Character(
        1, "Rick Sanchez", "Alive", "Human", "imageurl", Location("loc 1", "loc url")
    )
    val fakeCharacterMorty = Character(
        2, "Morty", "Alive", "Cartoon", "imageur2", Location("loc 2", "loc ur2")
    )

    val characterList = listOf(fakeCharacterRick, fakeCharacterMorty)
}