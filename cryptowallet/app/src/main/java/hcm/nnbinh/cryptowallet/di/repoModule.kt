package hcm.nnbinh.cryptowallet.di

import hcm.nnbinh.cryptowallet.repo.PriceRepo
import org.koin.dsl.module

val repoModule = module {
	single { PriceRepo(get(),get()) }
}