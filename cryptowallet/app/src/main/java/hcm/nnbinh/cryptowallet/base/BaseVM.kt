package hcm.nnbinh.cryptowallet.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hcm.nnbinh.cryptowallet.objects.Command
import hcm.nnbinh.cryptowallet.objects.SingleLiveEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

abstract class BaseVM : ViewModel() {
	protected val dispatcherIO by lazy { Dispatchers.IO }
	protected val dispatcherDefault by lazy { Dispatchers.Default }
	protected val dispatcherMain by lazy { Dispatchers.Main }
	private val _command by lazy { SingleLiveEvent<Command>() }
	fun getCommand() = _command
	fun setCommand(cmd: Command) {
		viewModelScope.launch(dispatcherMain) { _command.value = cmd }
	}
}