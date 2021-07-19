package hcm.nnbinh.cryptowallet.screens.price_detail

import hcm.nnbinh.cryptowallet.base.BaseVM
import hcm.nnbinh.cryptowallet.repo.PriceRepo

class PriceDetailVM(priceRepo: PriceRepo, base: String, name: String) : BaseVM() {
	val price = priceRepo.getPrice(base, name)
}