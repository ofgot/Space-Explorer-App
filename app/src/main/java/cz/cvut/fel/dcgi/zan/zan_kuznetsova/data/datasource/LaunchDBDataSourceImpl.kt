package cz.cvut.fel.dcgi.zan.zan_kuznetsova.data.datasource

import cz.cvut.fel.dcgi.zan.zan_kuznetsova.data.db.dao.LaunchDao
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.data.db.entity.LaunchEntity
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.data.local.Agency
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.data.local.Image
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.data.local.Launch
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.data.local.Rocket
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.data.local.RocketDetails
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.data.local.Status

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class LaunchDBDataSourceImpl(
    private val launchDao: LaunchDao
) : DBDataSource<Launch, String> {

    override fun getAll(): Flow<List<Launch>> =
        launchDao.getAllLaunches().map { entities ->
            entities.map { it.toLaunch() }
        }

    override suspend fun getById(id: String): Launch =
        launchDao.getLaunchForId(id).toLaunch()

    override suspend fun insertAll(data: List<Launch>) =
        launchDao.insertLaunches(
            data.map {
                it.toLaunchEntity()
            }
        )

    override suspend fun deleteAll() =
        launchDao.deleteAllLaunches()

    override suspend fun updateComment(id: Int, comment: String) {
        TODO("Not yet implemented")
    }

    override suspend fun getAllIds(): List<Int> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteNewsByIds(ids: List<Int>) {
        TODO("Not yet implemented")
    }

    override suspend fun hasAnyData(): Boolean = launchDao.hasAnyLaunches()
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

    videoUrls = videoUrls,
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
    ),

    videoUrls = this.videoUrls
)

