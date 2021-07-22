package hcm.nnbinh.cryptowallet.base

import hcm.nnbinh.cryptowallet.api.ApiService
import hcm.nnbinh.cryptowallet.database.CryptoDatabase
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

abstract class BaseRepo : KoinComponent {
	val apiService: ApiService by inject()
	val db: CryptoDatabase by inject()
}