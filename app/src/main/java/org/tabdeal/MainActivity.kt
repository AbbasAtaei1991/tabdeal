package org.tabdeal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private var lable = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findController().addOnDestinationChangedListener { _, destination, _ ->
            lable = destination.label.toString()
        }
    }

    private fun findController(): NavController {
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.NavHostFragment) as NavHostFragment
        return navHostFragment.navController
    }

    override fun onBackPressed() {
        finish()
    }
}