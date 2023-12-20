package com.pollyannawu.sunflower.data

import androidx.paging.PagingSource
import com.pollyannawu.sunflower.network.UnsplashApiService
import retrofit2.http.Query

class UnsplashPagingSource(private val service: UnsplashApiService, private val query: String):
    PagingSource {
}