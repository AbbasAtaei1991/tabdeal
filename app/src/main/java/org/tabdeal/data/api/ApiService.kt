package org.tabdeal.data.api

import org.tabdeal.data.model.UpdateRes
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("/version/")
    suspend fun checkForUpdate(): Response<UpdateRes>
}