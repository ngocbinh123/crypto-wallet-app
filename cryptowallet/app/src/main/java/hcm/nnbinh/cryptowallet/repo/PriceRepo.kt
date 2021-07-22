package hcm.nnbinh.cryptowallet.repo

import hcm.nnbinh.cryptowallet.base.BaseRepo
import hcm.nnbinh.cryptowallet.database.entity.Price

class PriceRepo : BaseRepo() {
	suspend fun getRemotePriceList() = apiService.getAllPrices()
	fun deleteAndInsertAll(items: List<Price>) = db.getPriceDao().deleteAndInsertAll(items)
	fun getPriceListFlow(word: String) = db.getPriceDao().getPricesFlow("%$word%")
	fun getPrice(base: String, name: String) = db.getPriceDao().getPriceLive(base, name)
}