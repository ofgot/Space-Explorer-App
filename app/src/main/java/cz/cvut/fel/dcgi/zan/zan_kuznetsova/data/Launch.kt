package cz.cvut.fel.dcgi.zan.zan_kuznetsova.data

data class Launch(
    val id: String,       // id
    val name: String,     // name
    val status: Status,   // status
    val net: String,      // net    // approximate or exact time
    val location: String,
    val webcastLive: String,    // webcast_live
    val image: Image,
    val agency: Agency?,
    val rocket: Rocket,
)

data class Status(
    val name: String,   // name
    val abbrev: String  // abbrev
)

data class Image(
    val name: String,   // name
    val url: String     // image_url
)

data class Agency(
    val name: String,               // name
    val abbrev: String,             // abbrev
    val country: String,            // country
    val description: String,        // description
    val logo: Image,                // logo
    val totalLaunchCount: Int,      // total_launch_count
    val successfulLaunches: Int,    // successful_launches
    val failedLaunches: Int,        // failed_launches
    val wikiUrl: String,            // wiki_url
    val infoUrl: String             // info_url
)

data class Rocket(
    val name: String,                    // full_name
    val rocketDetails: RocketDetails,
)

data class RocketDetails(
    val infoUrl: String,   // info_url
    val wikiUrl: String,   // wiki_url

    val height: Double,          // length
    val diameter: Double,        // diameter
    val maxStage: Int,          // max_stage
    val massToLEO: Double,       // leo_capacity
    val massToGTO: Double,       // gto_capacity
    val liftoffMass: Double,     // launch_mass
    val liftoffThrust: Double,   // to_thrust

    val successfulLaunches: Int, // successful_launches
    val maidenFlight: String,    // maiden_flight
    val failedLaunches: Int,     // failed_launches
)

