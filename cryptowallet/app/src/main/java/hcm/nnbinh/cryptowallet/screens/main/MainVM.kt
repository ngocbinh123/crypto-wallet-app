package hcm.nnbinh.cryptowallet.screens.main

import androidx.lifecycle.viewModelScope
import hcm.nnbinh.cryptowallet.base.BaseVM
import hcm.nnbinh.cryptowallet.objects.Command
import hcm.nnbinh.cryptowallet.repo.PriceRepo
import kotlinx.coroutines.launch
import java.util.Timer
import kotlin.concurrent.schedule

class MainVM(private val priceRepo: PriceRepo) : BaseVM() {
	private var scheduleTime = Timer()
	
	override fun onCleared() {
		startOrStopGetPriceListSchedule(false)
		super.onCleared()
	}
	
	internal fun startOrStopGetPriceListSchedule(isStart: Boolean) {
		scheduleTime.cancel()
		if (isStart) {
			scheduleTime = Timer()
			scheduleTime.schedule(0, 30000) {
				startGetRemotePriceList()
			}.run()
		}
	}
	
	private fun startGetRemotePriceList() {
		viewModelScope.launch(dispatcherIO) {
			try {
				val response = priceRepo.getRemotePriceList()
				val newPrices = response.data.map { it.toPriceEntity() }
				priceRepo.deleteAndInsertAll(newPrices)
			} catch (e: Exception) {
				e.printStackTrace()
				setCommand(Command.ShowError(e))
			}
		}
	}
}