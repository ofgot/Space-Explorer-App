package cz.cvut.fel.dcgi.zan.zan_kuznetsova.data.remote.source

import android.util.Log
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.data.local.*
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.data.remote.api.LaunchLibraryApi

class LaunchApiDataSource(
    private val api: LaunchLibraryApi
) {

    suspend fun getLaunches(): List<Launch> {
        val launches = api.getUpcomingLaunches().results

        // controlla
        launches.forEach { dto ->
            Log.e("API_ITEM", dto.toString())
        }

        return launches.map { dto ->
            val config = dto.rocket?.configuration

            val rocketDetails = try {
                config?.url?.let { url ->
                    api.getLauncherConfiguration(url)
                }
                Log.d("ROCKET_CONFIG_URL", config?.url ?: "null")
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
                    name = config?.full_name ?: config?.name ?: "",
                    rocketDetails = rocketDetails?.let {
                        RocketDetails(
                            infoUrl = config?.info_url ?: "",
                            wikiUrl = config?.wiki_url ?: "",
                            height = config?.length ?: 0.0,
                            diameter = config?.diameter ?: 0.0,
                            maxStage = config?.max_stage ?: 0,
                            massToLEO = config?.leo_capacity ?: 0.0,
                            massToGTO = config?.gto_capacity ?: 0.0,
                            liftoffMass = config?.launch_mass ?: 0.0,
                            liftoffThrust = config?.to_thrust ?: 0.0,
                            successfulLaunches = config?.successful_launches ?: 0,
                            maidenFlight = config?.maiden_flight ?: "",
                            failedLaunches = config?.failed_launches ?: 0
                        )
                    }
                ),

                videoUrls = dto.vidURLs?.map { it.url } ?: emptyList()
            )
        }
    }
}
