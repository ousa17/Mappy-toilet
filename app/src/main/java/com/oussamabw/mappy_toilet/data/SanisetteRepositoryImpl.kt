package com.oussamabw.mappy_toilet.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.oussamabw.mappy_toilet.data.network.SanisetteRecord
import com.oussamabw.mappy_toilet.data.network.SanisetteService
import com.oussamabw.mappy_toilet.data.source.SanisettePagingSource
import com.oussamabw.mappy_toilet.domain.SanisetteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class SanisetteRepositoryImpl @Inject constructor(private val service: SanisetteService) : SanisetteRepository {

    override fun getSanisettesStream(geoFilterDistance: String): Flow<PagingData<SanisetteRecord>> {
        return Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = 10),
            pagingSourceFactory = { SanisettePagingSource(service, geoFilterDistance) }
        ).flow
    }
}
