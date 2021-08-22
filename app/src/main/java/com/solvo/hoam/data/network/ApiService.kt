package com.solvo.hoam.data.network

import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by uvays on 04.08.2021.
 */

interface ApiService {

    companion object {
        const val BASE_URL = "https://hoam.ru"
    }

    @GET("/api/category")
    suspend fun getCategories(): List<CategoryResponse>

    @GET("/api/city")
    suspend fun getLocations(): List<LocationResponse>

    @GET("/api/ad")
    suspend fun getProducts(): ProductsResponse

    @GET("/api/ad/{id}")
    suspend fun getProduct(@Path("id") id: Long): ProductResponse
}