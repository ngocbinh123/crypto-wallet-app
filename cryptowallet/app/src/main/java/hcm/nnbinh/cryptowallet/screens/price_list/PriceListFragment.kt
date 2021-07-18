package hcm.nnbinh.cryptowallet.screens.price_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import hcm.nnbinh.cryptowallet.base.BaseFragment
import hcm.nnbinh.cryptowallet.databinding.FragmentPriceListBinding
import hcm.nnbinh.cryptowallet.helper.networkAvailableFlow
import kotlinx.coroutines.flow.collect
import org.koin.androidx.viewmodel.ext.android.viewModel

class PriceListFragment : BaseFragment() {
	private lateinit var binding: FragmentPriceListBinding
	private val priceListVM: PriceListVM by viewModel()
	
	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		binding = FragmentPriceListBinding.inflate(inflater, container, false)
		binding.lifecycleOwner = this
		
		lifecycleScope.launchWhenResumed {
			requireContext().networkAvailableFlow().collect {
				priceListVM.onNetworkStatusChange(it)
			}
		}
		priceListVM.startGetRemotePriceList()
		return binding.root
	}
	
	override fun setupViews() {
		binding.rcvPrices.adapter = PriceListAdapter()
	}
	
	override fun setupObservers() {
		priceListVM.getCommand().observe(this) { cmd ->
			processCommand(cmd)
		}
		priceListVM.priceList.observe(this) { newList ->
			(binding.rcvPrices.adapter as PriceListAdapter).submitList(newList)
		}
	}
}