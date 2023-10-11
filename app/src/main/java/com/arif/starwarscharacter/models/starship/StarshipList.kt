package com.arif.starwarscharacter.models.starship


import com.google.gson.annotations.SerializedName

data class StarshipList(
    @SerializedName("count")
    val count: Int,
    @SerializedName("next")
    val next: String,
    @SerializedName("previous")
    val previous: Any,
    @SerializedName("results")
    val starshipResults: List<StarshipResult>
)