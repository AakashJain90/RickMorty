package com.assessment.rickmorty.data.repository

import com.assessment.rickmorty.data.model.CharactersInternalResponse
import com.assessment.rickmorty.data.model.LocationInternalResponse

interface IDataRepository {

    suspend fun getCharacters() : CharactersInternalResponse

    suspend fun getLocationByUrl(url: String) : LocationInternalResponse
}