package hcm.nnbinh.cryptowallet.screens.price_list

import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import hcm.nnbinh.cryptowallet.base.BaseVM
import hcm.nnbinh.cryptowallet.database.entity.Price
import hcm.nnbinh.cryptowallet.usecase.CRUDPriceUseCase
import hcm.nnbinh.cryptowallet.usecase.SearchPriceUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class PriceListVM(
	private val searchPriceUseCase: SearchPriceUseCase,
	private val crudPriceUseCase: CRUDPriceUseCase,
) : BaseVM() {
	private val _keyWordFlow = MutableStateFlow("")
	
	@ExperimentalCoroutinesApi
	internal val displayPriceList = _keyWordFlow
		.flatMapLatest { word -> searchPriceUseCase.searchPricesFlow(word) }
		.catch { e -> e.printStackTrace() }
		.flowOn(dispatcherIO)
		.asLiveData(viewModelScope.coroutineContext)
	
	var debounceJob: Job? = null
	internal fun filterPricesBy(newText: String?) {
		debounceJob?.cancel()
		debounceJob = viewModelScope.launch(dispatcherDefault) {
			delay(500)
			val word = newText?.trim() ?: ""
			if (newText != _keyWordFlow.value) {
				_keyWordFlow.value = word
			}
			debounceJob = null
		}
	}
	
	internal fun updateBookMark(item: Price) {
		viewModelScope.launch(dispatcherDefault) {
			item.bookmark = !item.bookmark
			crudPriceUseCase.updateBookMarkFlow(item.base, item.name, item.bookmark)
		}
	}
}