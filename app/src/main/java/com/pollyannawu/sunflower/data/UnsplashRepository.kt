package com.pollyannawu.sunflower.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.pollyannawu.sunflower.network.UnsplashApiService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UnsplashRepository @Inject constructor(private val service: UnsplashApiService) {

    fun getSearchResultStream(query: String): Flow<PagingData<UnsplashPhoto>>{
        return Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = NETWORK_PAGE_SIZE),
            pagingSourceFactory = { UnsplashPagingSource(service, query)}
        ).flow
    }

    companion object{
        private const val NETWORK_PAGE_SIZE = 25
    }
}