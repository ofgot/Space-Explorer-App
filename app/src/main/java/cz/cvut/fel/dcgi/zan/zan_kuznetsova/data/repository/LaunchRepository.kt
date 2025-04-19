package cz.cvut.fel.dcgi.zan.zan_kuznetsova.data.repository

import cz.cvut.fel.dcgi.zan.zan_kuznetsova.data.db.datasource.LaunchDBDataSource
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.data.db.local.Launch

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
        launchDBDataSource.getLaunchById(id)

}
