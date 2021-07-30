package hcm.nnbinh.cryptowallet.di

import hcm.nnbinh.cryptowallet.screens.main.MainVM
import hcm.nnbinh.cryptowallet.screens.price_detail.PriceDetailVM
import hcm.nnbinh.cryptowallet.screens.price_list.PriceListVM
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
	viewModel { MainVM(get()) }
	viewModel { PriceListVM(get(), get()) }
	viewModel { (base: String, name: String) -> PriceDetailVM(get(), base, name) }
}