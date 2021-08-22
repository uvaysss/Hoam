package com.solvo.hoam.data.repository

import com.solvo.hoam.data.network.ApiService
import com.solvo.hoam.data.network.LocationResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Created by uvays on 15.08.2021.
 */

class LocationRepository @Inject constructor(
    private val apiService: ApiService
) {

    private val hashMap = mutableMapOf<Long, LocationResponse>()

    suspend fun fetchData() = withContext(Dispatchers.IO) {
        val list = apiService.getLocations()
        val map = list.associateBy { location -> location.id }
        hashMap.putAll(map)
    }

    fun get(id: Long): LocationResponse? {
        return hashMap[id]
    }
}