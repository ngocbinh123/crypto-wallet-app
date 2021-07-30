package hcm.nnbinh.cryptowallet.screens.price_list

import androidx.recyclerview.widget.DiffUtil
import hcm.nnbinh.cryptowallet.database.entity.Price

class PriceListDiffCallBack : DiffUtil.ItemCallback<Price>() {
	override fun areItemsTheSame(oldItem: Price, newItem: Price): Boolean {
		return oldItem.base == newItem.base && oldItem.name == newItem.name
	}
	
	override fun areContentsTheSame(oldItem: Price, newItem: Price): Boolean {
		return oldItem.sellPrice == oldItem.sellPrice &&
				oldItem.buyPrice == newItem.buyPrice &&
				oldItem.icon == newItem.icon &&
				oldItem.counter == newItem.counter &&
				oldItem.bookmark == newItem.bookmark
	}
}