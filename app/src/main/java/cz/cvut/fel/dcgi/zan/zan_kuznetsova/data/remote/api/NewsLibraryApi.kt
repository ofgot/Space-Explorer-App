package cz.cvut.fel.dcgi.zan.zan_kuznetsova.data.remote.api

import cz.cvut.fel.dcgi.zan.zan_kuznetsova.data.remote.dto.NewsApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsLibraryApi {

    @GET("v4/articles")
    suspend fun getNews(
        @Query("limit") limit: Int = 5,
    ): NewsApiResponse

}