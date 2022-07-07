package com.example.facebook.util

import android.app.Activity
import androidx.annotation.IdRes
import androidx.databinding.BindingAdapter
import androidx.fragment.app.Fragment
import androidx.navigation.*
import androidx.navigation.fragment.findNavController
import com.example.facebook.dataclasses.BaseResponse
import com.google.android.material.textfield.TextInputLayout
import retrofit2.Response

@BindingAdapter("errorMessage")
fun TextInputLayout.showErrorMessage(errorMessage: String?) {
    this.error = errorMessage
}

fun Activity.findNavController(@IdRes viewId: Int): NavController =
    Navigation.findNavController(this, viewId)

fun NavDirections.navigate(fragment: Fragment) {
    fragment.findNavController().navigate(this)
}

fun NavDestination.matches(
    @IdRes destId: Int
): Boolean {
    var currentDestination: NavDestination? = this
    while (currentDestination!!.id != destId && currentDestination.parent != null) {
        currentDestination = currentDestination.parent
    }
    return currentDestination.id == destId
}

typealias ApiResponse<R> = Response<BaseResponse<R>>
