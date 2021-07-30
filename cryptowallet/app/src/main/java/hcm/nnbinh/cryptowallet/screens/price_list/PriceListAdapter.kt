package hcm.nnbinh.cryptowallet.screens.price_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import hcm.nnbinh.cryptowallet.database.entity.Price
import hcm.nnbinh.cryptowallet.databinding.LayoutPriceItemBinding

class PriceListAdapter(
	private val viewModel: PriceListVM
) : ListAdapter<Price, PriceVH>(PriceListDiffCallBack()) {
	
	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PriceVH {
		val inflater = LayoutInflater.from(parent.context)
		val binding = LayoutPriceItemBinding.inflate(inflater, parent, false)
		return PriceVH(binding, viewModel)
	}
	
	override fun onBindViewHolder(holder: PriceVH, position: Int) {
		val item = getItem(position)
		holder.onBind(item)
	}
}