package hcm.nnbinh.cryptowallet.usecase

import hcm.nnbinh.cryptowallet.repo.PriceRepo
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class GetLocalPriceUseCase : KoinComponent {
	private val priceRepo: PriceRepo by inject()
	fun getLocalPriceLive(base: String, name: String) = priceRepo.getPriceLive(base, name)
}