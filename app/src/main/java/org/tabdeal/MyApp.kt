package org.tabdeal

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

@HiltAndroidApp
class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()
        plantTimber()
    }

    private fun plantTimber() {
        val applicationScope = CoroutineScope(Dispatchers.Default)
        applicationScope.launch {
            Timber.plant(Timber.DebugTree())
        }
    }
}