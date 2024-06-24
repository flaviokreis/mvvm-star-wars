package com.flaviokreis.datasource.commons.remote

import com.google.gson.annotations.SerializedName

internal data class PaginatedResponse<T>(
    @SerializedName("count")
    val count: Int = 0,

    @SerializedName("next")
    val next: String? = null,

    @SerializedName("previous")
    val previous: String? = null,

    @SerializedName("results")
    val results: List<T> = emptyList()
)