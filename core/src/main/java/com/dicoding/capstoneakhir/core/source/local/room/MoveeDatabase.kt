package com.dicoding.capstoneakhir.core.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dicoding.capstoneakhir.core.source.local.entity.MoveeEntity

@Database(entities = [MoveeEntity::class], version = 1, exportSchema = false)
abstract class MoveeDatabase : RoomDatabase() {

    abstract fun moveeDAO(): MoveeDAO

}