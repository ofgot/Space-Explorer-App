package cz.cvut.fel.dcgi.zan.zan_kuznetsova.data.db.datasource

import cz.cvut.fel.dcgi.zan.zan_kuznetsova.data.db.local.Launch

import kotlinx.coroutines.flow.Flow

interface LaunchDBDataSource {

    fun getAllLaunches() : Flow<List<Launch>>

    suspend fun getLaunchById(id: String) : Launch

    suspend fun insertLaunches(launches: List<Launch>)

    suspend fun deleteAllLaunches()
}

