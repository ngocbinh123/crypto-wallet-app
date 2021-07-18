package hcm.nnbinh.cryptowallet.screens.price_list

import androidx.lifecycle.viewModelScope
import hcm.nnbinh.cryptowallet.base.BaseVM
import hcm.nnbinh.cryptowallet.objects.Command
import hcm.nnbinh.cryptowallet.repo.PriceRepo
import kotlinx.coroutines.launch

class PriceListVM(private val priceRepo: PriceRepo) : BaseVM() {
	internal val priceList = priceRepo.getAllLive()
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