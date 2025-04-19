package cz.cvut.fel.dcgi.zan.zan_kuznetsova.data.db.datasource

import cz.cvut.fel.dcgi.zan.zan_kuznetsova.data.db.dao.LaunchDao
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.data.db.entity.LaunchEntity
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.data.db.local.Agency
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.data.db.local.Image
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.data.db.local.Launch
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.data.db.local.Rocket
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.data.db.local.RocketDetails
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.data.db.local.Status

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class LaunchDBDataSourceImpl(
    private val launchDao: LaunchDao
) : LaunchDBDataSource {

    override fun getAllLaunches(): Flow<List<Launch>> =
        launchDao.getAllLaunches().map { entities ->
            entities.map { it.toLaunch() }
        }

    override suspend fun getLaunchById(id: String): Launch =
        launchDao.getLaunchForId(id).toLaunch()

    override suspend fun insertLaunches(launches: List<Launch>) =
        launchDao.insertLaunches(
            launches.map {
                it.toLaunchEntity()
            }
        )


    override suspend fun deleteAllLaunches() =
        launchDao.deleteAllLaunches()
}


// Mapping

fun Launch.toLaunchEntity() = LaunchEntity(
    id = id,
    name = name,
    statusName = status.name,
    statusAbbrev = status.abbrev,
    net = net,
    location = location,
    webcastLive = webcastLive,
    imageUrl = image?.url ?: "",

    agencyName = agency?.name ?: "",
    agencyAbbrev = agency?.abbrev ?: "",
    agencyCountry = agency?.country ?: "",
    agencyDescription = agency?.description ?: "",
    agencyLogoUrl = agency?.logo?.url ?: "",
    agencyWikiUrl = agency?.wikiUrl ?: "",
    agencyInfoUrl = agency?.infoUrl ?: "",
    agencySuccessfulLaunches = agency?.successfulLaunches ?: 0,
    agencyFailedLaunches = agency?.failedLaunches ?: 0,
    agencyTotalLaunches = agency?.totalLaunchCount ?: 0,

    rocketName = rocket?.name ?: "",
    rocketHeight = rocket?.rocketDetails?.height ?: 0.0,
    rocketDiameter = rocket?.rocketDetails?.diameter ?: 0.0,
    rocketMaxStage = rocket?.rocketDetails?.maxStage ?: 0,
    rocketMassToLEO = rocket?.rocketDetails?.massToLEO ?: 0.0,
    rocketMassToGTO = rocket?.rocketDetails?.massToGTO ?: 0.0,
    rocketLiftoffMass = rocket?.rocketDetails?.liftoffMass ?: 0.0,
    rocketLiftoffThrust = rocket?.rocketDetails?.liftoffThrust ?: 0.0,
    rocketWikiUrl = rocket?.rocketDetails?.wikiUrl ?: "",
    rocketInfoUrl = rocket?.rocketDetails?.infoUrl ?: "",
    rocketMaidenFlight = rocket?.rocketDetails?.maidenFlight ?: "",
    rocketSuccessfulLaunches = rocket?.rocketDetails?.successfulLaunches ?: 0,
    rocketFailedLaunches = rocket?.rocketDetails?.failedLaunches ?: 0,
)

fun LaunchEntity.toLaunch() = Launch(
    id = id,
    name = name,
    status = Status(statusName, statusAbbrev),
    net = net,
    location = location,
    webcastLive = webcastLive,
    image = Image(name = "Image", url = imageUrl),
    agency = Agency(
        name = agencyName,
        abbrev = agencyAbbrev,
        country = agencyCountry,
        description = agencyDescription,
        logo = Image("Logo", agencyLogoUrl),
        totalLaunchCount = agencyTotalLaunches,
        successfulLaunches = agencySuccessfulLaunches,
        failedLaunches = agencyFailedLaunches,
        wikiUrl = agencyWikiUrl,
        infoUrl = agencyInfoUrl
    ),
    rocket = Rocket(
        name = rocketName,
        rocketDetails = RocketDetails(
            height = rocketHeight,
            diameter = rocketDiameter,
            maxStage = rocketMaxStage,
            massToLEO = rocketMassToLEO,
            massToGTO = rocketMassToGTO,
            liftoffMass = rocketLiftoffMass,
            liftoffThrust = rocketLiftoffThrust,
            wikiUrl = rocketWikiUrl,
            infoUrl = rocketInfoUrl,
            maidenFlight = rocketMaidenFlight,
            successfulLaunches = rocketSuccessfulLaunches,
            failedLaunches = rocketFailedLaunches
        )
    )
)

