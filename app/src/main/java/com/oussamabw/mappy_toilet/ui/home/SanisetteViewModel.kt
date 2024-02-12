package com.oussamabw.mappy_toilet.ui.home

import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.oussamabw.mappy_toilet.data.network.SanisetteRecord
import com.oussamabw.mappy_toilet.domain.GetSanisettesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class SanisetteViewModel @Inject constructor(
    private val getSanisettesUseCase: GetSanisettesUseCase,
    private val dispatcher: CoroutineDispatcher,
) : ViewModel() {

    private val _sanisetteState = MutableStateFlow(SanisetteState())
    val sanisetteState: MutableStateFlow<SanisetteState> = _sanisetteState

    fun onEvent(sanisetteEvent: SanisetteEvent) {
        when (sanisetteEvent) {
            is SanisetteEvent.LoadWithLatLon -> {
                updateGeoFilterDistance(
                    lat = sanisetteEvent.lat,
                    lon = sanisetteEvent.lon
                )
            }
        }
    }

    private fun updateGeoFilterDistance(lat: String, lon: String) {
        _sanisetteState.update {
            it.copy(
                geoFilterDistance = "$lat,$lon,1000"
            )
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    val sanisettes: Flow<PagingData<SanisetteRecord>> = _sanisetteState.flatMapLatest {
        getSanisettesUseCase(it.geoFilterDistance).flowOn(dispatcher)
    }
}
