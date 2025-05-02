package cz.cvut.fel.dcgi.zan.zan_kuznetsova.data.remote.dto

import com.squareup.moshi.Json

data class LauncherConfigurationDto(
    val url: String?,

    val name: String?,
    @Json(name = "full_name") val fullName: String?,

    val length: Double?,
    val diameter: Double?,

    val max_stage: Int?,
    val leo_capacity: Double?,
    val gto_capacity: Double?,
    val launch_mass: Double?,
    val to_thrust: Double?,

    val wiki_url: String?,
    val info_url: String?,

    val maiden_flight: String?,

    val successful_launches: Int?,
    val failed_launches: Int?
)