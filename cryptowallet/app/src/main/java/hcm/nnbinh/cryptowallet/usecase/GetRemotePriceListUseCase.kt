package hcm.nnbinh.cryptowallet.usecase

import hcm.nnbinh.cryptowallet.repo.PriceRepo
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class GetRemotePriceListUseCase : KoinComponent {
	private val priceRepo: PriceRepo by inject()
	suspend fun getAndSaveRemotePriceList() {
		val response = priceRepo.getRemotePriceList()
		val newPrices = response.data.map { it.toPriceEntity() }
		priceRepo.deleteAndInsertAll(newPrices)
	}
}