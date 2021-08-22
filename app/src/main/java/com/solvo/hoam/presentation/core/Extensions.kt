package com.solvo.hoam.presentation.core

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import io.reactivex.disposables.Disposable

/**
 * Created by uvays on 08/01/2021.
 */

fun Activity.hideKeyboard() {
    val view = currentFocus ?: View(this)
    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
    imm?.hideSoftInputFromWindow(view.windowToken, 0)
}

fun Fragment.hideKeyboard() {
    val activity = requireActivity()
    val windowToken = requireView().rootView.windowToken
    val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
    imm?.hideSoftInputFromWindow(windowToken, 0)
}

fun Disposable.disposeSafe() {
    if (isDisposed.not()) {
        dispose()
    }
}