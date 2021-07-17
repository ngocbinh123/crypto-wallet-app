package hcm.nnbinh.cryptowallet.api.response

import com.google.gson.annotations.SerializedName

data class PriceListResponse(
	@SerializedName("data")
	val data: List<PriceResponse>
)