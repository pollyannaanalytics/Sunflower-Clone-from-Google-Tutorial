package com.pollyannawu.sunflower.data.dataclass

import com.google.gson.annotations.SerializedName
import com.pollyannawu.sunflower.data.UnsplashUser

data class UnsplashPhoto(
    @field:SerializedName("id") val id: String,
    @field:SerializedName("urls") val urls: UnsplashPhotoUrls,
    @field:SerializedName("user") val user: UnsplashUser
)
