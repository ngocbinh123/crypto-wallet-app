package hcm.nnbinh.cryptowallet.screens.main

import androidx.lifecycle.viewModelScope
import hcm.nnbinh.cryptowallet.base.BaseActivityVM
import hcm.nnbinh.cryptowallet.objects.Command
import hcm.nnbinh.cryptowallet.repo.PriceRepo
import kotlinx.coroutines.launch

class MainVM(private val priceRepo: PriceRepo) : BaseActivityVM() {
	internal fun startGetRemotePriceList() {
		viewModelScope.launch(dispatcherIO) {
			try {
				val response = priceRepo.getRemotePriceList()
				val newPrices = response.data.map { it.toPriceEntity() }
				priceRepo.insert(newPrices)
			} catch (e: Exception) {
				e.printStackTrace()
				setCommand(Command.ShowError(e))
			}
		}
	}
	
	override fun onNetworkStatusChange(isOnline: Boolean) {
		super.onNetworkStatusChange(isOnline)
		if (isOnline) {
			startGetRemotePriceList()
		}
	}
}