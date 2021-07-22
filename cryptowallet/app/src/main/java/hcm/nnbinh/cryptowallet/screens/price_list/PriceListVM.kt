package hcm.nnbinh.cryptowallet.screens.price_list

import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import hcm.nnbinh.cryptowallet.base.BaseVM
import hcm.nnbinh.cryptowallet.usecase.SearchPriceUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOn

class PriceListVM(private val searchPriceUseCase: SearchPriceUseCase) : BaseVM() {
	private val _keyWordFlow = MutableStateFlow("")
	
	@ExperimentalCoroutinesApi
	internal val displayPriceList = _keyWordFlow
		.flatMapLatest { word -> searchPriceUseCase.searchPricesFlow(word) }
		.distinctUntilChanged()
		.catch { e -> e.printStackTrace() }
		.flowOn(dispatcherIO)
		.asLiveData(viewModelScope.coroutineContext)
	
	internal fun filterPricesBy(newText: String?) {
		val word = newText?.trim() ?: ""
		if (newText != _keyWordFlow.value) {
			_keyWordFlow.value = word
		}
	}
}