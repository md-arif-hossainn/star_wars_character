package com.arif.starwarscharacter.paging.planet

import StarWarApi
import androidx.paging.PagingSource
import androidx.paging.PagingState
import java.lang.Exception
import com.arif.starwarscharacter.models.planet.PlanetResult


class PlanetPagingSource(private val starWarApi: StarWarApi) : PagingSource<Int, PlanetResult>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PlanetResult> {
        return try {
            val position = params.key ?: 1
            val response = starWarApi.getPlanet(position)

            return LoadResult.Page(
                data = response.planetResults,
                prevKey = if (position == 1) null else position - 1,
                nextKey = if (position == 6) null else position + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, PlanetResult>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

}