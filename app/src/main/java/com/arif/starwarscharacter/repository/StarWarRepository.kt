package com.arif.paging_android.repository

import StarWarApi
import com.arif.starwarscharacter.paging.character.CharacterPagingSource
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.arif.starwarscharacter.paging.planet.PlanetPagingSource
import com.arif.starwarscharacter.paging.starship.StarshipPagingSource

class StarWarRepository(private val starWarApi: StarWarApi) {

    fun getCharacter() = Pager(
        config = PagingConfig(pageSize = 10, maxSize = 50),
        pagingSourceFactory = { CharacterPagingSource(starWarApi) }
    ).liveData

    fun getStarship() = Pager(
        config = PagingConfig(pageSize = 10, maxSize = 50),
        pagingSourceFactory = { StarshipPagingSource(starWarApi) }
    ).liveData

    fun getPlanet() = Pager(
        config = PagingConfig(pageSize = 10, maxSize = 50),
        pagingSourceFactory = { PlanetPagingSource(starWarApi) }
    ).liveData
}


