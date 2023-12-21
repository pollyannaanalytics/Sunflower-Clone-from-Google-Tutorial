package com.pollyannawu.sunflower.data.dataclass

import com.google.gson.annotations.SerializedName
import com.pollyannawu.sunflower.data.dataclass.UnsplashPhoto

data class UnsplashSearchResponse(
    @field:SerializedName("results") val results: List<UnsplashPhoto>,
    @field:SerializedName("total_pages") val totalPages: Int
)
