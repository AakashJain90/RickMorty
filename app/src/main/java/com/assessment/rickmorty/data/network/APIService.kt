package com.assessment.rickmorty.data.network

import com.assessment.rickmorty.data.model.CharactersServiceResponse
import com.assessment.rickmorty.data.model.Location
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

interface APIService {

    @GET("/api/character")
    suspend fun getCharacters(): Response<CharactersServiceResponse>

    @GET
    suspend fun getLocationByUrl(@Url url: String): Response<Location>
}
