package cz.cvut.fel.dcgi.zan.zan_kuznetsova.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "launches")
data class LaunchEntity(
    @PrimaryKey val id: String,
    val name: String,
    val statusName: String,
    val statusAbbrev: String,
    val net: String,
    val location: String,
    val webcastLive: String,
    val imageUrl: String,

    // Agency
    val agencyName: String,
    val agencyAbbrev: String,
    val agencyCountry: String,
    val agencyDescription: String,
    val agencyLogoUrl: String,
    val agencyWikiUrl: String,
    val agencyInfoUrl: String,
    val agencySuccessfulLaunches: Int,
    val agencyFailedLaunches: Int,
    val agencyTotalLaunches: Int,

    // Rocket
    val rocketName: String,
    val rocketHeight: Double,
    val rocketDiameter: Double,
    val rocketMaxStage: Int,
    val rocketMassToLEO: Double,
    val rocketMassToGTO: Double,
    val rocketLiftoffMass: Double,
    val rocketLiftoffThrust: Double,
    val rocketWikiUrl: String,
    val rocketInfoUrl: String,
    val rocketMaidenFlight: String,
    val rocketSuccessfulLaunches: Int,
    val rocketFailedLaunches: Int,
)
