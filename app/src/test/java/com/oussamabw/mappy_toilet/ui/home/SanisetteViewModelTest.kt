package com.oussamabw.mappy_toilet.ui.home


import androidx.paging.PagingData
import com.oussamabw.mappy_toilet.data.network.SanisetteRecord
import com.oussamabw.mappy_toilet.domain.GetSanisettesUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class SanisetteViewModelTest {

    private val mockGetSanisettesUseCase = mockk<GetSanisettesUseCase>()
    private lateinit var viewModel: SanisetteViewModel
    private val dispatcher = Dispatchers.Unconfined


    @Before
    fun setUp() {
        Dispatchers.setMain(StandardTestDispatcher())
    }

    @Test
    fun checkSendEventLatLon() = runTest {
        // Given
        val lat = "48.8566"
        val lon = "2.3522"
        val expectedGeoFilterDistance = "$lat,$lon,1000"
        coEvery { mockGetSanisettesUseCase(any()) } returns flowOf(PagingData.empty())
        viewModel = SanisetteViewModel(mockGetSanisettesUseCase, dispatcher)

        // When
        viewModel.onEvent(SanisetteEvent.LoadWithLatLon(lat, lon))

        // Then
        val state = viewModel.sanisetteState.value
        assertEquals(expectedGeoFilterDistance, state.geoFilterDistance)
    }

    @Test
    fun checkDataEmitted() = runTest {
        // Given
        val expectedObject = SanisetteRecord(
            datasetid = "1",
            recordid = "2",
            fields = null,
            geometry = null,
            record_timestamp = null
        )
        val mockPagingData = PagingData.from(listOf(expectedObject))
        val expectedFlowResponse = flowOf(mockPagingData)
        coEvery { mockGetSanisettesUseCase(any()) } returns expectedFlowResponse
        viewModel = SanisetteViewModel(mockGetSanisettesUseCase, dispatcher)
        val lat = "48.8566"
        val lon = "2.3522"
        viewModel.onEvent(SanisetteEvent.LoadWithLatLon(lat, lon))

        assertEquals(
            expectedFlowResponse.first(),
            viewModel.sanisettes.first())
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}
