package com.dicoding.capstoneakhir.core.source.local.room

import androidx.room.*
import com.dicoding.capstoneakhir.core.source.local.entity.MoveeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MoveeDAO {

    @Query("SELECT * FROM movee")
    fun getList(): Flow<List<MoveeEntity>>

    @Query("SELECT * FROM movee WHERE watchlist = 1")
    fun getWatchlist(): Flow<List<MoveeEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWatchlist(movies: List<MoveeEntity>)

    @Update
    fun updateWatchlist(entity: MoveeEntity)

}