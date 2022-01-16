package com.assessment.rickmorty.data.model

sealed class LocationInternalResponse {

    data class Success(val location: Location) : LocationInternalResponse()

    data class Fail(val errorMsg: String) : LocationInternalResponse()

}
