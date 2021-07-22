package hcm.nnbinh.cryptowallet.screens.price_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import hcm.nnbinh.cryptowallet.R
import hcm.nnbinh.cryptowallet.base.BaseActivity
import hcm.nnbinh.cryptowallet.base.BaseFragment
import hcm.nnbinh.cryptowallet.databinding.FragmentPriceListBinding
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.androidx.viewmodel.ext.android.viewModel


@ExperimentalCoroutinesApi
class PriceListFragment : BaseFragment(), OnQueryTextListener {
	private lateinit var binding: FragmentPriceListBinding
	private val priceListVM: PriceListVM by viewModel()
	
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setHasOptionsMenu(true)
	}
	
	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		binding = FragmentPriceListBinding.inflate(inflater, container, false)
		binding.lifecycleOwner = this
		return binding.root
	}
	
	override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
		inflater.inflate(R.menu.mn_price_list, menu)
		setupSearchView(menu)
	}
	
	private fun setupSearchView(menu: Menu) {
		val searchView = menu.findItem(R.id.action_search).actionView as SearchView
		searchView.setOnQueryTextListener(this)
	}
	
	override fun setupViews() {
		(requireActivity() as BaseActivity).setupActionBar(binding.toolbar, false)
		binding.rcvPrices.adapter = PriceListAdapter()
	}
	
	override fun setupObservers() {
		priceListVM.displayPriceList.observe(this) { newList ->
			(binding.rcvPrices.adapter as PriceListAdapter).submitList(newList)
		}
	}
	
	override fun onQueryTextSubmit(query: String?): Boolean {
		priceListVM.filterPricesBy(query)
		return true
	}
	
	override fun onQueryTextChange(newText: String?): Boolean {
		priceListVM.filterPricesBy(newText)
		return true
	}
}