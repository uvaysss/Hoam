package com.solvo.hoam.data.network

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Created by uvays on 15.08.2021.
 */

@JsonClass(generateAdapter = true)
data class CategoryResponse(
    @Json(name = "id") val id: Long,
    @Json(name = "name") val name: String,
    @Json(name = "parent_id") val parentId: Long?,
    @Json(name = "priority") val priority: Int,
    @Json(name = "childs") val children: List<CategoryResponse>?
)