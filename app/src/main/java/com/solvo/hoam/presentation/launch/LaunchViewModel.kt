package com.solvo.hoam.presentation.launch

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.solvo.hoam.data.repository.CategoryRepository
import com.solvo.hoam.data.repository.LocationRepository
import com.solvo.hoam.presentation.core.NetworkErrorHandler
import com.solvo.hoam.presentation.main.MainFlowKey
import com.zhuinden.simplestack.Backstack
import com.zhuinden.simplestack.History
import com.zhuinden.simplestack.StateChange
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by uvays on 15.08.2021.
 */

class LaunchViewModel @Inject constructor(
    private val locationRepository: LocationRepository,
    private val categoryRepository: CategoryRepository,
    private val networkErrorHandler: NetworkErrorHandler
) : ViewModel() {

    val refreshEnabledLiveData: MutableLiveData<Boolean> = MutableLiveData(false)

    private var fetchDataJob: Job? = null

    private lateinit var backstack: Backstack

    fun onStart(backstack: Backstack) {
        this.backstack = backstack

        fetchData()
    }

    fun onRefresh() {
        fetchData()
    }

    private fun fetchData() {
        fetchDataJob?.cancel()
        fetchDataJob = viewModelScope.launch {
            refreshEnabledLiveData.value = false

            try {
                locationRepository.fetchData()
                categoryRepository.fetchData()

                refreshEnabledLiveData.value = false

                backstack.setHistory(History.of(MainFlowKey()), StateChange.REPLACE)
            } catch (e: Exception) {
                networkErrorHandler.handle(e)

                refreshEnabledLiveData.value = true
            }
        }
    }

    override fun onCleared() {
        super.onCleared()

        fetchDataJob?.cancel()
    }
}