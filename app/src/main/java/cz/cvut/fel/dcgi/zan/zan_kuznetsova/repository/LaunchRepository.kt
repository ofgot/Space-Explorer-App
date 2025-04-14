package cz.cvut.fel.dcgi.zan.zan_kuznetsova.repository

import cz.cvut.fel.dcgi.zan.zan_kuznetsova.datasource.LaunchDBDataSource
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.local.Launch

class LaunchRepository(
    private val localDBDataSource: LaunchDBDataSource,
) {
    fun getAllLaunches() = localDBDataSource.getAllLaunches()

    suspend fun insertLaunches(launches: List<Launch>) {
        localDBDataSource.insertLaunches(launches)
    }

    suspend fun deleteAllLaunches() {
        localDBDataSource.deleteAllLaunches()
    }

    suspend fun getLaunchesForId(id: String) {
        localDBDataSource.getLaunchForId(id)
    }

}
