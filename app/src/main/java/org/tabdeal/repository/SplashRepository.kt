package org.tabdeal.repository

import org.tabdeal.data.api.SplashDataSource
import javax.inject.Inject

class SplashRepository @Inject constructor(
    private val dataSource: SplashDataSource
) {
    suspend fun checkForUpdate() = dataSource.checkForUpdate()
}