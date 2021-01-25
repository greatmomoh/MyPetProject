package com.example.mypetproject.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import com.example.mypetproject.R
import com.example.mypetproject.utils.navControllers
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val navController: NavController by navControllers(R.id.lessonsHostFragment)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            //TODO setup when you're ready for animation transitions
        }
    }
}
