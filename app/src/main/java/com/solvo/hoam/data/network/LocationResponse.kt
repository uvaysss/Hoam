package com.solvo.hoam.data.network

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Created by uvays on 15.08.2021.
 */

@JsonClass(generateAdapter = true)
data class LocationResponse(
    @Json(name = "id") val id: Long,
    @Json(name = "name") val name: String
)