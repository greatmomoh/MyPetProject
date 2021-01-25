package com.example.mypetproject.utils

import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.children
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment

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