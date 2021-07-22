package hcm.nnbinh.cryptowallet.screens.price_detail

import hcm.nnbinh.cryptowallet.base.BaseVM
import hcm.nnbinh.cryptowallet.usecase.GetLocalPriceUseCase

class PriceDetailVM(getPriceUseCase: GetLocalPriceUseCase, base: String, name: String) : BaseVM() {
	val price = getPriceUseCase.getLocalPriceLive(base, name)
}