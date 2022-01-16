package com.assessment.rickmorty.data.model

data class Location(val name: String, val url: String) {

    var residents: Array<String>? = null
    var dimension: String? = null
    var type: String? = null

    constructor(
        name: String,
        url: String,
        type: String,
        dimension: String,
        residents: Array<String>
    ) : this(name, url) {
        this.type = type
        this.dimension = dimension
        this.residents = residents
    }
}
