package org.tabdeal.data.model


import com.google.gson.annotations.SerializedName

data class VersionDetail(
    @SerializedName("version")
    val version: String?,
    @SerializedName("need_to_refresh")
    val needToRefresh: Boolean?,
    @SerializedName("platform")
    val platform: String?,
    @SerializedName("support_enabled")
    val supportEnabled: Boolean?
)