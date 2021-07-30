package hcm.nnbinh.cryptowallet.usecase

import hcm.nnbinh.cryptowallet.repo.PriceRepo
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class CRUDPriceUseCase : KoinComponent {
	private val priceRepo: PriceRepo by inject()
	fun updateBookMarkFlow(base: String, name: String, isBookmark: Boolean) =
		priceRepo.updateBookMark(base, name, isBookmark)
}