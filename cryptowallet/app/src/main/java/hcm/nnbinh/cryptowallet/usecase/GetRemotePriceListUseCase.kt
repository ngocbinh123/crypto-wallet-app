package hcm.nnbinh.cryptowallet.usecase

import hcm.nnbinh.cryptowallet.api.response.PriceListResponse
import hcm.nnbinh.cryptowallet.repo.PriceRepo
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class GetRemotePriceListUseCase : KoinComponent {
	private val priceRepo: PriceRepo by inject()
	fun getRemotePriceListFlow() = flow {
		val response = priceRepo.getRemotePriceList()
		emit(response)
	}.debounce(400)
	
	fun saveRemotePriceListFlow(response: PriceListResponse) = flow {
		val newPrices = response.data.map { it.toPriceEntity() }
		priceRepo.deleteAndInsertAll(newPrices)
		emit(newPrices)
	}
}