package com.pollyannawu.sunflower.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.pollyannawu.sunflower.network.UnsplashApiService
import retrofit2.http.Query

class UnsplashPagingSource(private val service: UnsplashApiService, private val query: String):
    PagingSource<Int, UnsplashPhoto>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UnsplashPhoto> {
        val page = params.key ?: UNSPLASH_STARTING_PAGE_INDEX
        return try {
            val response = service.getImages(query, page, params.loadSize)
            val photos = response.results

            LoadResult.Page(
                data = photos,
                prevKey = if(page == UNSPLASH_STARTING_PAGE_INDEX) null else page - 1,
                nextKey = if (page == response.totalPages) null else page + 1
            )
        }catch (e: Exception){
            LoadResult.Error(e)
        }
    }



    companion object{
        private const val UNSPLASH_STARTING_PAGE_INDEX = 1
    }
}