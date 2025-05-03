package cz.cvut.fel.dcgi.zan.zan_kuznetsova.data.remote.dto

import com.squareup.moshi.Json

data class LaunchApiResponse(
    val results: List<LaunchDto>
)

data class LaunchDto(
    val id: String,
    val name: String,
    val net: String,
    val status: StatusDto?,
    val pad: PadDto?,
    val image: String?,
    @Json(name = "launch_service_provider") val launchServiceProvider: AgencyDto?,
    val rocket: RocketDto?,

    val vidURLs: List<VideoUrlDto>?
)

data class VideoUrlDto(
    val url: String
)

data class StatusDto(
    val name: String?,
    val abbrev: String?
)

data class PadDto(
    val location: LocationDto?
)

data class LocationDto(
    val name: String?,
    @Json(name = "country_code") val country: String?
)

data class AgencyDto(
    val name: String?,
    val abbrev: String?,
    @Json(name = "country_code") val country: String?,
    val description: String?,
    @Json(name = "logo_url") val logoUrl: String?,
    @Json(name = "total_launch_count") val totalLaunchCount: Int?,
    @Json(name = "successful_launches") val successfulLaunches: Int?,
    @Json(name = "failed_launches") val failedLaunches: Int?,
    @Json(name = "wiki_url") val wikiUrl: String?,
    @Json(name = "info_url") val infoUrl: String?
)

data class RocketDto(
    val configuration: RocketConfigDto?
)

data class RocketConfigDto(
    val url: String?,
    val name: String?,
    @Json(name = "full_name") val fullName: String?,
    val length: Double?,
    val diameter: Double?,
    @Json(name = "max_stage") val maxStage: Int?,
    @Json(name = "leo_capacity") val massToLEO: Double?,
    @Json(name = "gto_capacity") val massToGTO: Double?,
    @Json(name = "launch_mass") val liftoffMass: Double?,
    @Json(name = "to_thrust") val liftoffThrust: Double?,
    @Json(name = "wiki_url") val wikiUrl: String?,
    @Json(name = "info_url") val infoUrl: String?,
    @Json(name = "maiden_flight") val maidenFlight: String?,
    @Json(name = "successful_launches") val successfulLaunches: Int?,
    @Json(name = "failed_launches") val failedLaunches: Int?
)
