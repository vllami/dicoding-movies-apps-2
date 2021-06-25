package com.dicoding.capstoneakhir.watchlist.di

import com.dicoding.capstoneakhir.watchlist.ui.WatchlistViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val watchlistModule = module {
    viewModel {
        WatchlistViewModel(get())
    }
}