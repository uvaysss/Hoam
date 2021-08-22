package com.solvo.hoam.data.repository

import com.solvo.hoam.data.network.ApiService
import com.solvo.hoam.data.network.CategoryResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Created by uvays on 15.08.2021.
 */

class CategoryRepository @Inject constructor(
    private val apiService: ApiService
) {
    private val hashMap = mutableMapOf<Long, CategoryResponse>()

    suspend fun fetchData() = withContext(Dispatchers.IO) {
        val list = apiService.getCategories()
        val map = list.associateBy { category -> category.id }
        hashMap.putAll(map)
    }

    fun getCategory(id: Long): CategoryResponse? {
        return hashMap[id]
    }
}