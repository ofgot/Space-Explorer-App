package cz.cvut.fel.dcgi.zan.zan_kuznetsova.data.repository

import cz.cvut.fel.dcgi.zan.zan_kuznetsova.data.datasource.LaunchDBDataSource
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.data.local.Launch

class LaunchRepository(
    private val launchDBDataSource: LaunchDBDataSource,
) {
    fun getAllLaunches() = launchDBDataSource.getAllLaunches()

    suspend fun insertLaunches(launches: List<Launch>) {
        launchDBDataSource.insertLaunches(launches)
    }

    suspend fun deleteAllLaunches() {
        launchDBDataSource.deleteAllLaunches()
    }

    suspend fun getLaunchesById(id: String) =
        launchDBDataSource.getLaunchForId(id)

}
