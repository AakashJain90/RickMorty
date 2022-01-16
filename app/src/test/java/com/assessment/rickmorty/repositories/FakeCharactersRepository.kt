package com.assessment.rickmorty.repositories

import com.assessment.rickmorty.data.model.Character
import com.assessment.rickmorty.data.model.CharactersInternalResponse
import com.assessment.rickmorty.data.model.Location
import com.assessment.rickmorty.data.model.LocationInternalResponse
import com.assessment.rickmorty.data.repository.IDataRepository

class FakeCharactersRepository(private val shouldReturnNetworkError: Boolean = false) :
    IDataRepository {

    val networkError = "Network Error"

    override suspend fun getCharacters(): CharactersInternalResponse {
        if (shouldReturnNetworkError) {
            return CharactersInternalResponse.Fail(networkError)
        }

        return CharactersInternalResponse.Success(characterList)
    }

    override suspend fun getLocationByUrl(url: String): LocationInternalResponse {
        TODO("Not yet implemented")
    }

    val fakeCharacterRick = Character(
        1, "Rick Sanchez", "Alive", "Human", "imageurl", Location("loc 1", "loc url")
    )
    val fakeCharacterMorty = Character(
        2, "Morty", "Alive", "Cartoon", "imageur2", Location("loc 2", "loc ur2")
    )
    val characterList = listOf(fakeCharacterRick, fakeCharacterMorty)
}