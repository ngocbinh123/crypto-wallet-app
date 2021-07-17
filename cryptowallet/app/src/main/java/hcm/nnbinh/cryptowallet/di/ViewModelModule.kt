package hcm.nnbinh.cryptowallet.di

import hcm.nnbinh.cryptowallet.screens.main.MainVM
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
	viewModel { MainVM(get()) }
}