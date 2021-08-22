package com.solvo.hoam.presentation.core

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by uvays on 04.08.2021.
 */

class NetworkErrorHandler @Inject constructor() {

    suspend fun handle(exception: Exception): String = withContext(Dispatchers.IO) {
        Timber.e(exception)

        return@withContext when (exception) {
            is CancellationException -> throw CancellationException(exception.message, exception)
            else -> "Проблемы со связью.\nПроверьте Ваше подключение\nк Интернету."
        }
    }
}