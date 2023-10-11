package com.arif.starwarscharacter.paging.character

import StarWarApi
import androidx.paging.PagingSource
import androidx.paging.PagingState
import java.lang.Exception
import com.arif.starwarscharacter.models.character.CharacterResult


class CharacterPagingSource(private val starWarApi: StarWarApi) : PagingSource<Int, CharacterResult>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharacterResult> {
        return try {
            val position = params.key ?: 1
            val response = starWarApi.getCharacter(position)

            return LoadResult.Page(
                data = response.characterResults,
                prevKey = if (position == 1) null else position - 1,
                nextKey = if (position == 9) null else position + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, CharacterResult>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

}