package com.dicoding.capstoneakhir.ui.details

import androidx.lifecycle.ViewModel
import com.dicoding.capstoneakhir.core.domain.model.Movee
import com.dicoding.capstoneakhir.core.domain.usecase.MoveeUseCase

class DetailsViewModel(private val moveeUseCase: MoveeUseCase) : ViewModel() {

    fun watchlist(movee: Movee, newState: Boolean) = moveeUseCase.setWatchlist(movee, newState)

}