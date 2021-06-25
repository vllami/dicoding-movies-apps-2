package com.dicoding.capstoneakhir.di

import com.dicoding.capstoneakhir.core.domain.usecase.MoveeInteractor
import com.dicoding.capstoneakhir.core.domain.usecase.MoveeUseCase
import com.dicoding.capstoneakhir.ui.details.DetailsViewModel
import com.dicoding.capstoneakhir.ui.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<MoveeUseCase> {
        MoveeInteractor(get())
    }
}

val viewModelModule = module {
    viewModel {
        MainViewModel(get())
    }

    viewModel {
        DetailsViewModel(get())
    }
}