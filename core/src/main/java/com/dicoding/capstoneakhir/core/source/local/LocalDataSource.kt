package com.dicoding.capstoneakhir.core.source.local

import com.dicoding.capstoneakhir.core.source.local.entity.MoveeEntity
import com.dicoding.capstoneakhir.core.source.local.room.MoveeDAO
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val moveeDAO: MoveeDAO) {

    fun getList(): Flow<List<MoveeEntity>> = moveeDAO.getList()

    fun getWatchlist(): Flow<List<MoveeEntity>> = moveeDAO.getWatchlist()

    suspend fun insertWatchlist(list: List<MoveeEntity>) = moveeDAO.insertWatchlist(list)

    fun setWatchlist(moveeEntity: MoveeEntity, newState: Boolean) {
        moveeEntity.apply {
            watchlist = newState
            moveeDAO.updateWatchlist(this)
        }
    }

}