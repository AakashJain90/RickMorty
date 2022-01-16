package com.assessment.rickmorty.data.model

sealed class CharactersInternalResponse {

    data class Success(val listCharacters: List<Character>) : CharactersInternalResponse()

    data class Fail(val errorMsg: String) : CharactersInternalResponse()

}
