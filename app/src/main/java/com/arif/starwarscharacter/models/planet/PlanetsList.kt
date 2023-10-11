package com.arif.starwarscharacter.models.planet


import com.google.gson.annotations.SerializedName

data class PlanetsList(
    @SerializedName("count")
    val count: Int,
    @SerializedName("next")
    val next: String,
    @SerializedName("previous")
    val previous: Any,
    @SerializedName("results")
    val planetResults: List<PlanetResult>
)