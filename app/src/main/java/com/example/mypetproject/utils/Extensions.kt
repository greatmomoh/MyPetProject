package com.example.mypetproject.utils

import android.app.Activity
import android.graphics.Color
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.children
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow

fun AppCompatActivity.navControllers(id: Int): Lazy<NavController> {
    return lazy {
        val navHost: NavHostFragment =
            supportFragmentManager.findFragmentById(id) as NavHostFragment
        navHost.navController
    }
}

fun ViewGroup.recursivelyApplyClickListener(
    ignore: List<Int> = emptyList(),
    function: (child: View) -> Unit
) {
    this.children
        .filter {
            ignore.contains(it.id).not()
        }
        .forEach {
            it.setOnClickListener {
                function.invoke(it)
            }
            if (it is ViewGroup) {
                it.recursivelyApplyToChildren(ignore, function)
            }
        }
}

fun ViewGroup.recursivelyApplyToChildren(
    ignore: List<Int> = emptyList(),
    function: (child: View) -> Unit
) {
    this.children
        .filter {
            ignore.contains(it.id).not()
        }
        .forEach {
            function.invoke(it)
            if (it is ViewGroup) {
                it.recursivelyApplyToChildren(ignore, function)
            }
        }
}

@ExperimentalCoroutinesApi
fun <T> MutableStateFlow<T>.updateValue(updateFn: T.() -> T): T {
    val updatedValue = updateFn(this.value)
    this.value = updatedValue
    return updatedValue
}

val Fragment.viewLifecycleScope
    get() = viewLifecycleOwner.lifecycleScope

fun Fragment.showSnackBar(
    snackBarText: String,
    timeLength: Int = Snackbar.LENGTH_LONG,
    topGravity: Boolean = true
) {
    activity?.showSnackBar(snackBarText, timeLength, topGravity)
}

fun Activity.showSnackBar(
    snackBarText: String,
    timeLength: Int = Snackbar.LENGTH_LONG,
    topGravity: Boolean = true
) {
    this.let {
        val snack = Snackbar.make(it.findViewById(android.R.id.content), snackBarText, timeLength)
        val view = snack.view
        if (topGravity) {
            val params = view.layoutParams as FrameLayout.LayoutParams
            params.gravity = Gravity.TOP or Gravity.CENTER_HORIZONTAL
            view.layoutParams = params
        }
        val tv = view.findViewById<View>(com.google.android.material.R.id.snackbar_text) as TextView
        tv.setTextColor(Color.WHITE)
        snack.show()
    }
}