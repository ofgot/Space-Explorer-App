package cz.cvut.fel.dcgi.zan.zan_kuznetsova.data.remote

import cz.cvut.fel.dcgi.zan.zan_kuznetsova.data.remote.dto.LaunchApiResponse
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.data.remote.dto.LauncherConfigurationDto
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface LaunchLibraryApi {
    @GET("launch/upcoming")
    suspend fun getUpcomingLaunches(
        @Query("limit") limit: Int = 1
    ): LaunchApiResponse

    @GET
    suspend fun getLauncherConfiguration(@Url url: String): LauncherConfigurationDto
}
