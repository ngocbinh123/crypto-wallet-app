package hcm.nnbinh.cryptowallet.screens.main

import androidx.lifecycle.viewModelScope
import hcm.nnbinh.cryptowallet.base.BaseVM
import hcm.nnbinh.cryptowallet.objects.Command
import hcm.nnbinh.cryptowallet.usecase.GetRemotePriceListUseCase
import kotlinx.coroutines.launch
import java.util.Timer
import kotlin.concurrent.schedule

class MainVM(private val getRemotePricesUseCase: GetRemotePriceListUseCase) : BaseVM() {
	private var _scheduleTime = Timer()
	
	override fun onCleared() {
		startOrStopGetPriceListSchedule(false)
		super.onCleared()
	}
	
	internal fun startOrStopGetPriceListSchedule(isStart: Boolean) {
		_scheduleTime.cancel()
		if (isStart) {
			_scheduleTime = Timer()
			_scheduleTime.schedule(0, 30000) {
				startGetRemotePriceList()
			}.run()
		}
	}
	
	private fun startGetRemotePriceList() {
		viewModelScope.launch(dispatcherIO) {
			try {
				getRemotePricesUseCase.getAndSaveRemotePriceList()
			} catch (e: Exception) {
				setCommand(Command.ShowError(e))
			}
		}
	}
}