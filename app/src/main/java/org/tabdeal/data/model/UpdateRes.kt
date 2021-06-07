package org.tabdeal.data.model


import com.google.gson.annotations.SerializedName

data class UpdateRes(
    @SerializedName("version")
    val version: String?,
    @SerializedName("need_to_refresh")
    val needToRefresh: Boolean?,
    @SerializedName("all_versions")
    val allVersions: List<VersionDetail>?
)