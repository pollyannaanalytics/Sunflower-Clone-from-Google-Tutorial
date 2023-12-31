package com.pollyannawu.sunflower.data.dataclass

import com.google.gson.annotations.SerializedName

data class UnsplashUser(
    @field:SerializedName("name") val name: String,
    @field:SerializedName("username")val username: String
){
    val attributeUrl: String
        get() {
            return "https://unsplash.com/$username?utm_source=sunflower&utm_medium=referral"
        }
}