package com.assessment.rickmorty.data.repository

import android.content.Context
import com.assessment.rickmorty.data.network.APIService
import com.assessment.rickmorty.R
import com.assessment.rickmorty.data.model.CharactersInternalResponse
import com.assessment.rickmorty.data.model.LocationInternalResponse
import com.assessment.rickmorty.utils.isNetworkAvailable

class DataRepository(
    private val context: Context,
    private val apiService: APIService
) : IDataRepository {

    override suspend fun getCharacters(): CharactersInternalResponse {
        if (!context.isNetworkAvailable()) {
            return CharactersInternalResponse.Fail(context.getString(R.string.network_unavail))
        }

        // fetch from network
        val retroResponse = apiService.getCharacters()

        // failure checks
        if (!retroResponse.isSuccessful) {
            return CharactersInternalResponse.Fail(retroResponse.message())
        }
        val serviceResponse = retroResponse.body()
            ?: return CharactersInternalResponse.Fail(context.getString(R.string.error_fetching_characters))

        return CharactersInternalResponse.Success(serviceResponse.results.orEmpty())
    }

    override suspend fun getLocationByUrl(url: String): LocationInternalResponse {
        if (!context.isNetworkAvailable()) {
            return LocationInternalResponse.Fail(context.getString(R.string.network_unavail))
        }

        // fetch from network
        val retroResponse = apiService.getLocationByUrl(url)

        // failure checks
        if (!retroResponse.isSuccessful) {
            return LocationInternalResponse.Fail(retroResponse.message())
        }
        val serviceResponse = retroResponse.body()
            ?: return LocationInternalResponse.Fail(context.getString(R.string.error_fetching_characters))

        return LocationInternalResponse.Success(serviceResponse)
    }

}