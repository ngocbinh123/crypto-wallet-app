package hcm.nnbinh.cryptowallet.screens.price_list

import androidx.navigation.findNavController
import hcm.nnbinh.cryptowallet.base.BaseVH
import hcm.nnbinh.cryptowallet.database.entity.Price
import hcm.nnbinh.cryptowallet.databinding.LayoutPriceItemBinding
import hcm.nnbinh.cryptowallet.screens.main.MainVM

class PriceVH(private val binding: LayoutPriceItemBinding,
private val viewModel: PriceListVM) :
	BaseVH<Price>(binding.root) {
	override fun onBind(item: Price) {
		binding.item = item
		binding.root.setOnClickListener { v ->
			val action = PriceListFragmentDirections.actionPriceListFragmentToPriceDetailFragment(
				item.base, item.name)
			v.findNavController().navigate(action)
		}
		binding.ivBookmark.setOnClickListener {
			viewModel.updateBookMark(item)
		}
		binding.executePendingBindings()
	}
}