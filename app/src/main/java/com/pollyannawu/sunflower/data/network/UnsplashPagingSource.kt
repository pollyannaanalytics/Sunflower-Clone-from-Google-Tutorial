package com.pollyannawu.sunflower.data.network

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.pollyannawu.sunflower.data.dataclass.UnsplashPhoto
import com.pollyannawu.sunflower.network.UnsplashApiService

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

    override fun getRefreshKey(state: PagingState<Int, UnsplashPhoto>): Int? {
       return state.anchorPosition?.let { anchorPosition ->
           state.closestPageToPosition(anchorPosition)?.prevKey
       }
    }

    companion object{
        private const val UNSPLASH_STARTING_PAGE_INDEX = 1
    }
}