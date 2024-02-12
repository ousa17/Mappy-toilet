package com.oussamabw.mappy_toilet.data.network

import com.google.gson.annotations.SerializedName

data class SanisetteResponse(
    val nhits: Int,
    val parameters: Parameters,
    @SerializedName("records")
    val sanisetteRecords: List<SanisetteRecord>,
)

data class Parameters(
    val dataset: String,
    val timezone: String,
    val rows: Int,
    val format: String,
    val geofilterDistance: List<String>
)

data class SanisetteRecord(
    val datasetid: String,
    val recordid: String,
    val fields: SanisetteFields?,
    val geometry: Geometry?,
    val record_timestamp: String?
)

data class SanisetteFields(
    val arrondissement: Int,
    val adresse: String,
    val horaire: String?,
    val acces_pmr: String?,
    val relais_bebe: String?,
    val geo_point_2d: List<Double>
)

data class Geometry(
    val type: String,
    val coordinates: List<Double>
)

