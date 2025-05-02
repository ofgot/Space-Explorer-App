package cz.cvut.fel.dcgi.zan.zan_kuznetsova.data.remote.datasource

import android.util.Log
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.data.local.*
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.data.remote.LaunchLibraryApi
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.data.remote.dto.LaunchDto

class LaunchApiDataSource(
    private val api: LaunchLibraryApi
) {

    suspend fun getLaunches(): List<Launch> {
        val launches = api.getUpcomingLaunches().results

        // controlla
        launches.forEach { dto ->
            Log.d("API_ITEM", dto.toString())
        }


        return launches.map { dto ->
            val config = dto.rocket?.configuration

            val rocketDetails = try {
                config?.url?.let { api.getLauncherConfiguration(it) }
            } catch (e: Exception) {
                Log.e("API_ERROR", "Failed to load rocket config: ${e.message}")
                null
            }


            Launch(
                id = dto.id,
                name = dto.name,
                net = dto.net,
                status = Status(
                    name = dto.status?.name ?: "",
                    abbrev = dto.status?.abbrev ?: ""
                ),
                location = dto.pad?.location?.name ?: "",
//                webcastLive = dto.webcast_live ?: "",

                image = dto.image?.let { url ->
                    Image(name = "Launch image", url = url)
                },

                agency = dto.launchServiceProvider?.let {
                    Agency(
                        name = it.name ?: "",
                        abbrev = it.abbrev ?: "",
                        country = "",
                        description = "",
                        logo = null,
                        totalLaunchCount = 0,
                        successfulLaunches = 0,
                        failedLaunches = 0,
                        wikiUrl = "",
                        infoUrl = ""
                    )
                },

                rocket = Rocket(
                    name = config?.fullName ?: config?.name ?: "",
                    rocketDetails = rocketDetails?.let {
                        RocketDetails(
                            infoUrl = it.info_url ?: "",
                            wikiUrl = it.wiki_url ?: "",
                            height = it.length ?: 0.0,
                            diameter = it.diameter ?: 0.0,
                            maxStage = it.max_stage ?: 0,
                            massToLEO = it.leo_capacity ?: 0.0,
                            massToGTO = it.gto_capacity ?: 0.0,
                            liftoffMass = it.to_thrust ?: 0.0,
                            liftoffThrust = it.to_thrust ?: 0.0,
                            successfulLaunches = it.successful_launches ?: 0,
                            maidenFlight = it.maiden_flight ?: "",
                            failedLaunches = it.failed_launches ?: 0
                        )
                    }
                )
            )
        }
    }
}
