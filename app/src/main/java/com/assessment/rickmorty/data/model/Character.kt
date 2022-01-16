package com.assessment.rickmorty.data.model

data class Character(val id: Int, val name: String, val status: String, val species: String, val image: String, var location: Location)
