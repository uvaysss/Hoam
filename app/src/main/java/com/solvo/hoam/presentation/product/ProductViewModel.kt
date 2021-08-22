package com.solvo.hoam.presentation.product

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.solvo.hoam.data.network.ApiService
import com.solvo.hoam.data.network.ProductResponse
import com.solvo.hoam.presentation.core.NetworkErrorHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Created by uvays on 05.08.2021.
 */

class ProductViewModel @Inject constructor(
    private val apiService: ApiService,
    private val networkErrorHandler: NetworkErrorHandler
) : ViewModel() {

    val loadingLiveData: MutableLiveData<Boolean> = MutableLiveData(false)
    val refreshEnabledLiveData: MutableLiveData<Boolean> = MutableLiveData(false)
    val contentLiveData: MutableLiveData<Boolean> = MutableLiveData(false)
    val dataLiveData: MutableLiveData<ProductResponse> = MutableLiveData(null)

    private var fetchDataJob: Job? = null

    private var id: Long = -1L

    fun onStart(key: ProductKey) {
        this.id = key.id

        fetchData()
    }

    fun onRefresh() {
        fetchData()
    }

    private fun fetchData() {
        fetchDataJob?.cancel()
        fetchDataJob = viewModelScope.launch {
            loadingLiveData.value = true
            contentLiveData.value = false
            refreshEnabledLiveData.value = false

            try {
                val product = withContext(Dispatchers.IO) {
                    apiService.getProduct(id)
                }

                loadingLiveData.value = false
                contentLiveData.value = true
                refreshEnabledLiveData.value = false
                dataLiveData.value = product
            } catch (e: Exception) {
                networkErrorHandler.handle(e)

                loadingLiveData.value = false
                contentLiveData.value = false
                refreshEnabledLiveData.value = true
            }
        }
    }

    override fun onCleared() {
        super.onCleared()

        fetchDataJob?.cancel()
    }
}