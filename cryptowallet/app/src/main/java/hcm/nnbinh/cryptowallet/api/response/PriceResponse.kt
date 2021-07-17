package hcm.nnbinh.cryptowallet.api.response

import com.google.gson.annotations.SerializedName
import hcm.nnbinh.cryptowallet.database.entity.Price

data class PriceResponse(
	@SerializedName("sell_price")
	val sellPrice: String,
	
	@SerializedName("icon")
	val icon: String,
	
	@SerializedName("name")
	val name: String,
	
	@SerializedName("buy_price")
	val buyPrice: String,
	
	@SerializedName("counter")
	val counter: String,
	
	@SerializedName("base")
	val base: String
) {
	internal fun toPriceEntity() = Price(sellPrice, icon, name, buyPrice, counter, base)
}