package hcm.nnbinh.cryptowallet.screens.price_list

import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import hcm.nnbinh.cryptowallet.base.BaseVM
import hcm.nnbinh.cryptowallet.objects.Command
import hcm.nnbinh.cryptowallet.repo.PriceRepo
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class PriceListVM(private val priceRepo: PriceRepo) : BaseVM() {
	private val _keyWordFlow = MutableStateFlow("")
	internal val displayPriceList = _keyWordFlow
		.flatMapLatest { word -> priceRepo.getPriceListFlow(word) }
		.distinctUntilChanged()
		.catch { e -> e.printStackTrace() }
		.flowOn(dispatcherIO)
		.asLiveData(viewModelScope.coroutineContext)
	
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
	
	internal fun filterPricesBy(newText: String?) {
		val words = newText?.trim() ?: ""
		if (newText != _keyWordFlow.value) {
			_keyWordFlow.value = words
		}
	}
	
	override fun onNetworkStatusChange(isOnline: Boolean) {
		super.onNetworkStatusChange(isOnline)
		if (isOnline) {
			startGetRemotePriceList()
		}
	}
}