package com.oussamabw.mappy_toilet.domain

import androidx.paging.PagingData
import com.oussamabw.mappy_toilet.data.network.SanisetteRecord
import kotlinx.coroutines.flow.Flow

interface SanisetteRepository {
    fun getSanisettesStream(geoFilterDistance: String): Flow<PagingData<SanisetteRecord>>
}
