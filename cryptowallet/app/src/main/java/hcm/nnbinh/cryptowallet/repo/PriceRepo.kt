package hcm.nnbinh.cryptowallet.repo

import hcm.nnbinh.cryptowallet.api.ApiService
import hcm.nnbinh.cryptowallet.database.CryptoDatabase
import hcm.nnbinh.cryptowallet.database.entity.Price

class PriceRepo(private val apiService: ApiService, private val db: CryptoDatabase) {
	suspend fun getRemotePriceList() = apiService.getAllPrices()
	suspend fun insert(items: List<Price>) = db.getPriceDao().insert(*items.toTypedArray())
	fun getAllLive() = db.getPriceDao().getAllLive()
	fun getPrice(base: String, name: String) = db.getPriceDao().getPriceLive(base, name)
}