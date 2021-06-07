package org.tabdeal.data.api

import javax.inject.Inject

class SplashDataSource @Inject constructor(
    private val apiService: ApiService
) : BaseDataSource() {
    suspend fun checkForUpdate() = getResult { apiService.checkForUpdate() }
}