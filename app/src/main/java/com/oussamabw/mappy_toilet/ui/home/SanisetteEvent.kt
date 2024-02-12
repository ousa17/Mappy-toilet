package com.oussamabw.mappy_toilet.ui.home

sealed class SanisetteEvent {
    data class LoadWithLatLon(val lat: String, val lon: String) : SanisetteEvent()
}
