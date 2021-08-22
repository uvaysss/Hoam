package com.solvo.hoam.presentation.core

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.solvo.hoam.presentation.launch.LaunchViewModel
import com.solvo.hoam.presentation.product.ProductViewModel
import com.solvo.hoam.presentation.productlist.ProductListViewModel
import javax.inject.Inject
import javax.inject.Provider

/**
 * Created by uvays on 02/02/2021.
 */

class AppViewModelFactory @Inject constructor(
        private val productListViewModelProvider: Provider<ProductListViewModel>,
        private val productViewModelProvider: Provider<ProductViewModel>,
        private val launchViewModelProvider: Provider<LaunchViewModel>
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val viewModel = when (modelClass) {
            ProductListViewModel::class.java -> productListViewModelProvider.get()
            ProductViewModel::class.java -> productViewModelProvider.get()
            LaunchViewModel::class.java -> launchViewModelProvider.get()
            else -> throw RuntimeException("unsupported view model class: $modelClass")
        }

        return viewModel as T
    }
}