package hcm.nnbinh.cryptowallet.di

import hcm.nnbinh.cryptowallet.usecase.GetLocalPriceUseCase
import hcm.nnbinh.cryptowallet.usecase.GetRemotePriceListUseCase
import hcm.nnbinh.cryptowallet.usecase.SearchPriceUseCase
import org.koin.dsl.module

val useCaseModule = module {
	single { GetRemotePriceListUseCase() }
	single { SearchPriceUseCase() }
	single { GetLocalPriceUseCase() }
}