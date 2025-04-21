package cz.cvut.fel.dcgi.zan.zan_kuznetsova.data.repository

import cz.cvut.fel.dcgi.zan.zan_kuznetsova.data.datasource.DBDataSource
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.data.local.Launch

class LaunchRepository(
    private val launchDBDataSource: DBDataSource<Launch, String>,
) {
    fun getAllLaunches() = launchDBDataSource.getAll()

    suspend fun insertLaunches(launches: List<Launch>) {
        launchDBDataSource.insertAll(launches)
    }

    suspend fun deleteAllLaunches() {
        launchDBDataSource.deleteAll()
    }

    suspend fun getLaunchesById(id: String) =
        launchDBDataSource.getById(id)

}
