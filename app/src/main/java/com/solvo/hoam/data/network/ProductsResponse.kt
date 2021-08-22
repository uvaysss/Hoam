package com.solvo.hoam.data.network

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Created by uvays on 04.08.2021.
 */

@JsonClass(generateAdapter = true)
data class ProductsResponse(
    @Json(name = "data") val products: List<ProductResponse>
)

@JsonClass(generateAdapter = true)
data class ProductResponse(
    @Json(name = "id") val id: Long,
    @Json(name = "title") val title: String,
    @Json(name = "text") val text: String,
    @Json(name = "price") val price: Long?,
    @Json(name = "phone") val phone: String,
    @Json(name = "views") val views: Int,
    @Json(name = "author_id") val author_id: String?,
    @Json(name = "author_name") val author_name: String,
    @Json(name = "category_id") val categoryId: Long,
    @Json(name = "city_id") val cityId: Long,
    @Json(name = "created_at") val createdAt: String,
    @Json(name = "images") val images: List<ProductImageResponse>
)

@JsonClass(generateAdapter = true)
data class ProductImageResponse(
    @Json(name = "id") val id: Long,
    @Json(name = "ad_id") val adId: Long,
    @Json(name = "is_main") val isMain: Int,
    @Json(name = "big") val big: String,
    @Json(name = "small") val small: String
)
