package com.arif.starwarscharacter.paging.starship

import StarWarApi
import androidx.paging.PagingSource
import androidx.paging.PagingState
import java.lang.Exception
import com.arif.starwarscharacter.models.starship.StarshipResult


class StarshipPagingSource(private val starWarApi: StarWarApi) : PagingSource<Int, StarshipResult>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, StarshipResult> {
        return try {
            val position = params.key ?: 1
            val response = starWarApi.getStarShip(position)

            return LoadResult.Page(
                data = response.starshipResults,
                prevKey = if (position == 1) null else position - 1,
                nextKey = if (position == 4) null else position + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, StarshipResult>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

}