package hcm.nnbinh.cryptowallet.repo

import hcm.nnbinh.cryptowallet.base.BaseRepo
import hcm.nnbinh.cryptowallet.database.entity.Price

class PriceRepo : BaseRepo() {
	suspend fun getRemotePriceList() = apiService.getAllPrices()
	fun updateBookMark(base: String, name: String, isBookmark: Boolean) = db.getPriceDao().updateBookMark(base, name, isBookmark)
	fun deleteAndInsertAll(items: List<Price>) = db.getPriceDao().deleteAndInsertAll(items)
	fun getPriceListFlow(word: String) = db.getPriceDao().getPricesFlow("%$word%")
	fun getPriceLive(base: String, name: String) = db.getPriceDao().getPriceLive(base, name)
}