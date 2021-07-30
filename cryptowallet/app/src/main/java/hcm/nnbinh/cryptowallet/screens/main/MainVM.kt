package hcm.nnbinh.cryptowallet.screens.main

import androidx.lifecycle.viewModelScope
import hcm.nnbinh.cryptowallet.base.BaseVM
import hcm.nnbinh.cryptowallet.objects.Command
import hcm.nnbinh.cryptowallet.objects.ProcessState
import hcm.nnbinh.cryptowallet.usecase.GetRemotePriceListUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.util.Timer
import kotlin.concurrent.schedule

@ExperimentalCoroutinesApi
class MainVM(private val getRemotePricesUseCase: GetRemotePriceListUseCase) : BaseVM() {
	private var _scheduleTime = Timer()
	
	override fun onCleared() {
		startOrStopGetPriceListSchedule(false)
		super.onCleared()
	}
	
	internal fun startOrStopGetPriceListSchedule(isStart: Boolean) {
		_scheduleTime.cancel()
		if (isStart) {
			_scheduleTime.cancel()
			_scheduleTime = Timer()
			_scheduleTime.schedule(0, 10000) {
				startGetRemotePriceList()
			}.run()
		}
	}
	
	private fun startGetRemotePriceList() {
		viewModelScope.launch(dispatcherIO) {
			getRemotePricesUseCase.getRemotePriceListFlow()
				.flatMapLatest {
					getRemotePricesUseCase.saveRemotePriceListFlow(it)
				}
				.flatMapLatest {
					flow {
						emit(ProcessState.Success(it))
					}
				}
				.onStart {
					setCommand(Command.ShowHideLoadingBar())
				}
				.onCompletion {
					setCommand(Command.ShowHideLoadingBar(false))
				}
				.catch { e ->
					e.printStackTrace()
					setCommand(Command.ShowError(e))
				}
				.flowOn(dispatcherIO)
				.stateIn(viewModelScope, SharingStarted.Lazily, ProcessState.Loading)
				.collect()
		}
	}
}