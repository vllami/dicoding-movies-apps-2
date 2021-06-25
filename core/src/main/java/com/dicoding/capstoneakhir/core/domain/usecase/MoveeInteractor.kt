package com.dicoding.capstoneakhir.core.domain.usecase

import com.dicoding.capstoneakhir.core.domain.model.Movee
import com.dicoding.capstoneakhir.core.domain.repository.IMoveeRepository

class MoveeInteractor(private val iMoveeRepository: IMoveeRepository) : MoveeUseCase {

    override fun getList() = iMoveeRepository.getList()

    override fun getWatchlist() = iMoveeRepository.getWatchlist()

    override fun setWatchlist(movee: Movee, state: Boolean) = iMoveeRepository.setWatchlist(movee, state)

}