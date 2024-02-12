package com.oussamabw.mappy_toilet.domain

import androidx.paging.PagingData
import com.oussamabw.mappy_toilet.data.network.SanisetteRecord
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetSanisettesUseCase @Inject constructor(private val repository: SanisetteRepository) {

    operator fun invoke(
        geofilterDistance: String
    ): Flow<PagingData<SanisetteRecord>> {
        return repository.getSanisettesStream(geofilterDistance)
    }
}
