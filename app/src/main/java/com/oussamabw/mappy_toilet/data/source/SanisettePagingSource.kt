package com.oussamabw.mappy_toilet.data.source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.oussamabw.mappy_toilet.data.network.SanisetteRecord
import com.oussamabw.mappy_toilet.data.network.SanisetteService
import java.io.IOException
import javax.inject.Singleton

@Singleton
class SanisettePagingSource(
    private val service: SanisetteService,
    private val geofilterDistance: String
) : PagingSource<Int, SanisetteRecord>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, SanisetteRecord> {
        val startPosition = params.key ?: STARTING_PAGE_INDEX
        return try {
            val response = service.getSanisettes(geofilterDistance, startPosition)
            val sanisettes = response.body()?.sanisetteRecords.orEmpty()
            LoadResult.Page(
                data = sanisettes,
                prevKey = if (startPosition == STARTING_PAGE_INDEX) null else startPosition - 10,
                nextKey = if (sanisettes.isEmpty()) null else startPosition + 10
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, SanisetteRecord>): Int? {
        return state.anchorPosition
    }

    companion object {
        private const val STARTING_PAGE_INDEX = 1
    }
}
