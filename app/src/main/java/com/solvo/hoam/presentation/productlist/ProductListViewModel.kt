package com.solvo.hoam.presentation.productlist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jakewharton.rxrelay2.BehaviorRelay
import com.solvo.hoam.App
import com.solvo.hoam.data.network.ApiService
import com.solvo.hoam.data.network.ProductResponse
import com.solvo.hoam.domain.interactor.AdListInteractor
import com.solvo.hoam.domain.model.AdEntity
import com.solvo.hoam.presentation.core.NetworkErrorHandler
import com.solvo.hoam.presentation.core.disposeSafe
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.coroutines.*
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by uvays on 02/02/2021.
 */

class ProductListViewModel @Inject constructor(
    private val apiService: ApiService,
    private val networkErrorHandler: NetworkErrorHandler
) : ViewModel() {

    val loadingLiveData: MutableLiveData<Boolean> = MutableLiveData(false)
    val contentLiveData: MutableLiveData<Boolean> = MutableLiveData(false)
    val itemLiveData: MutableLiveData<List<ProductResponse>> = MutableLiveData(emptyList())

    private var fetchDataJob: Job? = null

    fun onStart() {
        fetchData()
    }

    private fun fetchData() {
        fetchDataJob?.cancel()
        fetchDataJob = viewModelScope.launch {
            loadingLiveData.value = true
            contentLiveData.value = false
//            itemLiveData.value = emptyList()

            try {
                val response = withContext(Dispatchers.IO) {
                    delay(1000L)
                    apiService.getProducts()
                }

                loadingLiveData.value = false
                contentLiveData.value = true
                itemLiveData.value = response.products
            } catch (e: Exception) {
                networkErrorHandler.handle(e)

                loadingLiveData.value = false
                contentLiveData.value = false
                itemLiveData.value = emptyList()
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        fetchDataJob?.cancel()
    }
}