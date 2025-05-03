package cz.cvut.fel.dcgi.zan.zan_kuznetsova.data.local

data class Launch(
    val id: String = "",       // id
    val name: String = "",     // name
    val status: Status,   // status
    val net: String = "",      // net    // approximate or exact time
    val location: String = "",
    val webcastLive: String = "",    // webcast_live
    val image: Image?,     // ASK ABOUT THIS
    val agency: Agency?,
    val rocket: Rocket?,

    val videoUrls: List<String> = emptyList()
)

data class Status(
    val name: String = "",   // name
    val abbrev: String = ""  // abbrev
)

data class Image(
    val name: String = "",   // name
    val url: String = ""    // image_url
)

data class Agency(
    val name: String = "",              // name
    val abbrev: String = "",            // abbrev
    val country: String = "",           // country
    val description: String = "",       // description
    val logo: Image?,                   // logo
    val totalLaunchCount: Int = 0,      // total_launch_count
    val successfulLaunches: Int = 0,    // successful_launches
    val failedLaunches: Int = 0,        // failed_launches
    val wikiUrl: String = "",           // wiki_url
    val infoUrl: String = ""            // info_url
)

data class Rocket(
    val name: String = "",                    // full_name
    val rocketDetails: RocketDetails?,
)

data class RocketDetails(
    val infoUrl: String = "",   // info_url
    val wikiUrl: String = "",   // wiki_url

    val height: Double  = 0.0,          // height
    val diameter: Double = 0.0,        // diameter
    val maxStage: Int = 0,          // max_stage
    val massToLEO: Double = 0.0,       // leo_capacity
    val massToGTO: Double = 0.0,       // gto_capacity
    val liftoffMass: Double = 0.0,     // launch_mass
    val liftoffThrust: Double = 0.0,   // to_thrust

    val successfulLaunches: Int = 0, // successful_launches
    val maidenFlight: String = "",    // maiden_flight
    val failedLaunches: Int = 0,     // failed_launches
)

