package com.example.dcardhomework.data

import com.example.dcardhomework.data.Repo
import com.google.gson.annotations.SerializedName

data class SearchResponse(
    @SerializedName("total_count") val totalCount: Int,
    @SerializedName("incomplete_results") val incompleteResults: Boolean,
    val items: List<Repo>
)