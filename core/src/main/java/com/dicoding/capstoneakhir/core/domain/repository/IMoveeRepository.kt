package com.dicoding.capstoneakhir.core.domain.repository

import com.dicoding.capstoneakhir.core.domain.model.Movee
import com.dicoding.capstoneakhir.core.source.Resource
import kotlinx.coroutines.flow.Flow

interface IMoveeRepository {

    fun getList(): Flow<Resource<List<Movee>>>

    fun getWatchlist(): Flow<List<Movee>>

    fun setWatchlist(movee: Movee, state: Boolean)

}