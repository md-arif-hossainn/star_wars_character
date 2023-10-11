package com.arif.starwarscharacter.models.character


import com.google.gson.annotations.SerializedName

data class CharacterList(
    @SerializedName("count")
    val count: Int,
    @SerializedName("next")
    val next: String,
    @SerializedName("previous")
    val previous: Any,
    @SerializedName("results")
    val characterResults: List<CharacterResult>
)