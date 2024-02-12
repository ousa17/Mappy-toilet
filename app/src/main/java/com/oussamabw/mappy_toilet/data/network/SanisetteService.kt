package com.oussamabw.mappy_toilet.data.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SanisetteService {

    @GET("records/1.0/search/?dataset=sanisettesparis2011")
    suspend fun getSanisettes(
        @Query("geofilter.distance") geofilterDistance: String,
        @Query("start") start: Int
    ): Response<SanisetteResponse>
}
