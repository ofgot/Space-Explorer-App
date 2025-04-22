package cz.cvut.fel.dcgi.zan.zan_kuznetsova.data.datasource

import kotlinx.coroutines.flow.Flow

interface DBDataSource<T, ID> {

    fun getAll(): Flow<List<T>>

    suspend fun getById(id: ID): T

    suspend fun insertAll(data: List<T>)

    suspend fun deleteAll()

    suspend fun updateComment(id: Int, comment: String)

    suspend fun hasAnyNews(): Boolean
}
